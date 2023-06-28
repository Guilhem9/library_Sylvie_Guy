package com.library.libraryapp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Borrow {
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private static int nextBorrowID = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long borrowID;


    public Borrow(Member member, Document document, Library library) {
        this.member = member;
        this.document = document;
        this.library = library;
        this.borrowDate = LocalDate.now();
        this.expectedReturnDate = borrowDate.plusDays(21);
    }
    public Borrow(Member member, Document document, Library library, LocalDate borrowDate, LocalDate expectedReturnDate) {
        this(member,document, library);
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public long getBorrowID() {
        return borrowID;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public boolean isLate() {
        return actualReturnDate != null && expectedReturnDate.isBefore(actualReturnDate);
    }

    public Integer daysLate() {
        return (int) ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);
    }

    public void setActualReturnDate(LocalDate date) {
        this.actualReturnDate = date;
    }

    public Document getDocument() {
        return document;
    }
}
