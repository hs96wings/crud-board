package com.example.instagram_server.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JwtAuthFilter extends GenericFilter {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Set<String> protectedUrls = Set.of("/api/video/add");
        String path = ((HttpServletRequest) request).getRequestURI();

        // 인증이 필요한 경우에만 필터 작동
        if (protectedUrls.contains(path)) {
            String token = httpServletRequest.getHeader("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write("Missing or invalid token");
                return;
            }

            try {
                String jwtToken = token.substring(7);
                // 토큰 검증 및 claims 추출
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtToken)
                        .getBody();

                // Authentication 객체 생성
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_admin"));
                UserDetails userDetails = new User(claims.getSubject(), "", authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write("Invalid or expired token");
                return;
            }
        }

        // 나머지는 그냥 통과
        chain.doFilter(request, response);
    }
}
