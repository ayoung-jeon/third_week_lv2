package com.sparta.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentalRequestDto {
    // 넣어주는(입력 받는) 값


    private Long bookId;
    private Long memberId;

    public RentalRequestDto(Long bookId, Long memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

}
