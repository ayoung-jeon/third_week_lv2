package com.sparta.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRequestDto {

    private String title;
    private String writer;
    private String language;
    private String publisher;

    public BookRequestDto(String title, String writer, String language, String publisher) {
        this.title = title;
        this.writer = writer;
        this.language = language;
        this.publisher = publisher;
    }
}
