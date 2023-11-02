package com.sparta.book.service;


import com.sparta.book.dto.MemberRequestDto;
import com.sparta.book.dto.MemberResponseDto;
import com.sparta.book.entity.Member;
import com.sparta.book.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        // RequestDto -> Entity
        Member member = new Member(requestDto);

        // DB 저장
        Member saveMember = memberRepository.save(member);

        // Entity -> ResponseDto
        MemberResponseDto memeberResponseDto = new MemberResponseDto(saveMember);

        return memeberResponseDto;
    }

    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원정보는 없습니다. memberId=" + memberId));
        return new MemberResponseDto(member);
    }

    public List<MemberResponseDto> getMembers() {
        // DB 조회
        return memberRepository.findAllByOrderByModifiedAtAsc().stream().map(MemberResponseDto::new).toList();
    }
}
