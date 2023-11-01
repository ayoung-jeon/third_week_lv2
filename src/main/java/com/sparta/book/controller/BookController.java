package com.sparta.book.controller;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")  // 도서 등록
    public BookResponseDto createBook(@RequestBody BookRequestDto requestDto) {
        return bookService.createBook(requestDto);
    }

    @GetMapping("/books/{bookId}")  // 도서 조회
    public BookResponseDto getBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/books") // 도서 목록 조회
    public List<BookResponseDto> getBooks() {
        return bookService.getBooks();
    }
}
