package com.sparta.book.entity;

import com.sparta.book.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity  // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "book") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Book extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;  // 도서 식별값

    @Column(name = "title", nullable = false)
    private String title;  // 제목

    @Column(name = "writer", nullable = false)
    private String writer;  // 저자

    @Column(name = "language", nullable = false)
    private String language;  // 언어

    @Column(name = "publisher", nullable = false)
    private String publisher;  // 출판사

    @Column(name = "is_Available", nullable = false)
    private boolean isAvailable = true;  // 대출 가능 여부

    public Book(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.language = requestDto.getLanguage();
        this.publisher = requestDto.getPublisher();
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void update(BookRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.language = requestDto.getLanguage();
        this.publisher = requestDto.getPublisher();
    }
}
