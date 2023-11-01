package com.sparta.book.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    // 넣어주는(입력 받는) 값

    private String name;
    private String gender;
    private String residentRegistrationNumber;
    private String phoneNumber;
    private String address;

    public MemberRequestDto(String name, String gender, String residentRegistrationNumber, String phoneNumber, String address) {
        this.name = name;
        this.gender = gender;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
