package com.sparta.book.service;


import com.sparta.book.dto.RentalRequestDto;
import com.sparta.book.dto.RentalResponseDto;
import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import com.sparta.book.entity.Rental;
import com.sparta.book.repository.BookRepository;
import com.sparta.book.repository.MemberRepository;
import com.sparta.book.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public RentalService(RentalRepository rentalRepository,
                         BookRepository bookRepository, MemberRepository memberRepository) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional  // 도서 대출
    public RentalResponseDto bookRental(RentalRequestDto requestDto) {

        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));

        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        // 현재 도서가 대출 상태라면 대출이 불가능 조건
        if (!book.isAvailable()) {
            throw new IllegalArgumentException("이미 대출된 책입니다.");
        }

        // 회원이 반납하지 않은 책이 있다면 대출이 불가능 조건
        List<Rental> existingRentals = rentalRepository.findByMemberAndReturnedDateIsNull(member);
        if (!existingRentals.isEmpty()) {
            throw new IllegalArgumentException("회원님은 현재 대출 상태입니다. 회원 ID: " + member.getMemberId());
        }

        // 대출 정보 생성
        Rental rental = new Rental(book, member);
        rental.setDueDate(LocalDate.now().plusDays(7)); // 7일 후로 반납 기한 설정

        // 대출 정보 저장
        Rental saveRental = rentalRepository.save(rental);

        // 책 상태 업데이트
        book.setAvailable(false);
        bookRepository.save(book);

        return new RentalResponseDto(saveRental);
    }

    @Transactional(readOnly = true)  // 조회만 하는 경우
    // 대출 내역 조회
    public RentalResponseDto getRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("대출내역이 없습니다. rentalId=" + rentalId));

        rental.returnBook();
        return new RentalResponseDto(rental);
    }

    // 대출 내역 전체 조회 (오름차순)
    public List<RentalResponseDto> getRental() {
        return rentalRepository.findAllByOrderByModifiedAtAsc().stream().map(RentalResponseDto::new).toList();
    }

    @Transactional // 반납 처리
    public RentalResponseDto returnBook(Long rentalId) {
        // 대출 기록 찾기
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("대출 기록이 없습니다. rentalId=" + rentalId));

        // 책 상태 업데이트
        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        // 변경된 대출 기록을 반환하기 전에 Rental 인스턴스를 삭제하려고 추가해본 코드임
        RentalResponseDto responseDto = new RentalResponseDto(rental);
        rentalRepository.delete(rental);

        return responseDto;
    }
}
