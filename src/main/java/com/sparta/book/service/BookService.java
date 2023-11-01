package com.sparta.book.service;

import com.sparta.book.dto.BookRequestDto;
import com.sparta.book.dto.BookResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto createBook(BookRequestDto requestDto) {
        // RequestDto -> Entity
        Book book = new Book(requestDto);

        // DB 저장
        Book saveBook = bookRepository.save(book);

        // Entity -> ResponseDto
        BookResponseDto bookResponseDto = new BookResponseDto(saveBook);

        return bookResponseDto;
    }

    public BookResponseDto getBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서는 없습니다. bookId=" + bookId));
        return new BookResponseDto(book);
    }

    public List<BookResponseDto> getBooks() {
        // DB 조회
        return bookRepository.findAllByOrderByModifiedAtAsc().stream().map(BookResponseDto::new).toList();
    }

//    private Book findBook(Long bookId) {
//        return BookRepository.findById(bookId).orElseThrow(() ->
//                new IllegalArgumentException("선택한 도서는 존재하지 않습니다.")
//        );
//    }
}
