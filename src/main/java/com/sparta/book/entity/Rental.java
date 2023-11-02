package com.sparta.book.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rental")
public class Rental extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @OneToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    // isReturned 필드는 도서의 대출 상태를 나타냄
    // 초기값은 false로 설정하여 새로운 대출 건이 생성될 때 도서가 대출 상태임을 나타냄
    @Column(nullable = false)
    private boolean isReturned = false;

    @Column
    private LocalDate dueDate;  // 반납 예정일

    @Column
    private LocalDate returnedDate;  // 실제 반납일

    public Rental(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.isReturned = false;
    }

    // 반납일을 필드의 값을 현재 시간으로 설정
    public void returnBook() {
        this.isReturned = true;
        this.returnedDate = LocalDate.now(); // 현재 사용 날짜를 표시한다.
    }
}
