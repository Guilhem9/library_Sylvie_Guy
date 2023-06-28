package com.library.libraryapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String borrowID;

    private LocalDate loanDate;
    private LocalDate returnDate;

    @ManyToOne
    private Document document;

    @ManyToOne
    private Member member;
}