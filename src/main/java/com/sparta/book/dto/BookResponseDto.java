package com.sparta.book.dto;

import com.sparta.book.entity.Book;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookResponseDto {

    private Long bookId;  // 도서 식별값
    private String title;  // 제목
    private String writer;  // 저자
    private String language;  // 언어
    private String publisher;  // 출판사
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BookResponseDto(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.writer = book.getWriter();
        this.language = book.getLanguage();
        this.publisher = book.getPublisher();
        this.createdAt = book.getCreatedAt();
        this.modifiedAt = book.getModifiedAt();
    }
}
