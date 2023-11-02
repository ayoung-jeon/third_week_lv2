package com.sparta.book.dto;

import com.sparta.book.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    // 내보내는(보여주는) 값

    private Long memberId;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;


    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.gender = member.getGender();
        this.phoneNumber = member.getPhoneNumber();
        this.address = member.getAddress();

    }
}
