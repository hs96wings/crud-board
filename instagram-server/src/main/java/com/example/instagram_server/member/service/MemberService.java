package com.example.instagram_server.member.service;

import com.example.instagram_server.member.domain.Member;
import com.example.instagram_server.member.dto.MemberSaveReqDto;
import com.example.instagram_server.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(MemberSaveReqDto memberSaveReqDto) {
        // 이미 가입되어 있는 아이디 검증
        if (memberRepository.findByUserid(memberSaveReqDto.getUserid()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다");
        }

        Member newMember = Member.builder()
                .userid(memberSaveReqDto.getUserid())
                .password(memberSaveReqDto.getPassword())
                .build();
        return memberRepository.save(newMember);
    }
}
