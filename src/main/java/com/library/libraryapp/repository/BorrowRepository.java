package com.library.libraryapp.repository;

import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByMember(Member member);
}
