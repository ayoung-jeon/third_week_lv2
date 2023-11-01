package com.sparta.book.controller;


import com.sparta.book.dto.MemberRequestDto;
import com.sparta.book.dto.MemberResponseDto;
import com.sparta.book.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")  // 회원 등록
    public MemberResponseDto createMember(@RequestBody MemberRequestDto requestDto) {
        return memberService.createMember(requestDto);
    }

    @GetMapping("/members/{membersId}")  // 회원 조회
    public MemberResponseDto getMember(@PathVariable Long membersId) {
        return memberService.getMember(membersId);
    }

    @GetMapping("/members") // 회원 전제 조회
    public List<MemberResponseDto> getMembers() {
        return memberService.getMembers();
    }
}
