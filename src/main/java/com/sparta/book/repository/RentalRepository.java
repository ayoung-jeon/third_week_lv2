package com.sparta.book.repository;

import com.sparta.book.entity.Book;
import com.sparta.book.entity.Member;
import com.sparta.book.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long>{
    List<Rental> findAllByOrderByModifiedAtAsc();
    // 회원과 연관된 모든 대출 내역 찾기
    List<Rental> findByMember(Member member);

    // 특정 회원과 특정 책에 대한 대출 내역 찾기
    List<Rental> findByMemberAndReturnedDateIsNull(Member member);
}

