package com.sparta.book.controller;


import com.sparta.book.dto.RentalRequestDto;
import com.sparta.book.dto.RentalResponseDto;
import com.sparta.book.service.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService= rentalService;
    }

    @PostMapping("/rental")  // 도서 대출
    public RentalResponseDto bookRental(@RequestBody RentalRequestDto requestDto) {
        return rentalService.bookRental(requestDto);
    }

    @GetMapping("rental/{rentalId}")  // 대출 내역 조회
    public RentalResponseDto getRental(@PathVariable Long rentalId) {
        return rentalService.getRental(rentalId);
    }

    @GetMapping("/rental")  // 대출 목록 조회 (오름차순)
    public List<RentalResponseDto> getRental() {
        return rentalService.getRental();
    }
}
