package com.example.instagram_server.member.controller;

import com.example.instagram_server.member.domain.Member;
import com.example.instagram_server.member.dto.MemberSaveReqDto;
import com.example.instagram_server.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 어드민 아이디만 있기 때문에 가입이 필요없긴 하지만 일단 테스트를 위함
    // 나중에 어드민 아이디가 더 생길 수도 있고
    @PostMapping("/create")
    public ResponseEntity<?> memberCreate(@RequestBody MemberSaveReqDto memberSaveReqDto) {
        Member member = memberService.create(memberSaveReqDto);
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }
}
