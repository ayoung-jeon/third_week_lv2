package com.sparta.book.repository;

import com.sparta.book.entity.Member;
import com.sparta.book.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface RentalRepository extends JpaRepository<Rental, Long>{
    List<Rental> findAllByOrderByModifiedAtAsc();
    List<Rental> findByMemberAndIsReturnedFalse(Member member);

}
