package com.library.libraryapp.repository;

import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    List<Borrow> findByMemberId(Long memberId);
}
