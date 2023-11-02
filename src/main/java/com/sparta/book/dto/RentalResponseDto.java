package com.sparta.book.dto;

import com.sparta.book.entity.Rental;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentalResponseDto {
    // 내보내는(보여주는) 값

    private Long rentalId; // 대출 ID
    private Long bookId;   // 도서 식별값
    private Long memberId;  // 회원 식별값
    private boolean isReturned;  // 대출 상태
    private LocalDateTime createdAt;  //대출한 시간
    private LocalDateTime dueDate;  // 반납 예정일
    // 과제 필수 부분
    private String memberName;  // 회원이름
    private String memberPhoneNumber;  // 회원의 전화번호
    private String bookTitle;  // 책 제목
    private String bookWriter;  // 저자

    public RentalResponseDto(Rental rental) {
        this.rentalId = rental.getRentalId();  // rental Id
        this.bookId = rental.getBook().getBookId();  // Book 엔터티의 식별값을 얻음
        this.memberId = rental.getMember().getMemberId();  // Member 엔터티의 식별값을 얻음
        this.isReturned = rental.getBook().isAvailable();  // 대출 상태
        this.createdAt = rental.getCreatedAt();  // 대출 날짜와 시간
        this.dueDate = rental.getDueDate();  // 반납예정일
        // 필수 구현 부분
        this.memberName = rental.getMember().getName();
        this.memberPhoneNumber = rental.getMember().getPhoneNumber();
        this.bookTitle = rental.getBook().getTitle();
        this.bookWriter = rental.getBook().getWriter();
    }
}
