package com.sparta.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.book.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity  // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "member")  // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;  // 회원 식별값

    @Column(name = "name", nullable = false)
    private String name;  // 이름

    @Column(name = "gender", nullable = false)
    private String gender;  // 성별

    @Column(name = "residentRegistrationNumber", nullable = false, unique = true)
    private String residentRegistrationNumber;  // 주민번호

    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;  // 연락처

    @Column(name = "address", nullable = false)
    private String address;  // 주소

    // rental 과 연결
    @JsonIgnore // JSON 직렬화 시 무한 루프가 발생하지 않도록
    @OneToOne(mappedBy = "member") //!
    private Rental rental;

    private LocalDate modifiedAt;

    public Member(MemberRequestDto requestDto) {
        this.name = requestDto.getName();
        this.gender = requestDto.getGender();
        this.residentRegistrationNumber = requestDto.getResidentRegistrationNumber();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
    }
}
