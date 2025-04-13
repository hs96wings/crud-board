package com.example.instagram_server.member.controller;

import com.example.instagram_server.common.auth.JwtTokenProvider;
import com.example.instagram_server.member.domain.Member;
import com.example.instagram_server.member.dto.MemberLoginReqDto;
import com.example.instagram_server.member.dto.MemberSaveReqDto;
import com.example.instagram_server.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 어드민 아이디만 있기 때문에 가입이 필요없긴 하지만 일단 테스트를 위함
    // 나중에 어드민 아이디가 더 생길 수도 있고
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto) {
        Member member = memberService.create(memberSaveReqDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody MemberLoginReqDto memberLoginReqDto) {
        // userid, password 검증
        Member member = memberService.login(memberLoginReqDto);

        // 일치할경우 access 토큰 발행
        String jwtToken = jwtTokenProvider.createToken(member.getUserid());
        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("id", member.getId());
        loginInfo.put("token", jwtToken);

        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }
}
