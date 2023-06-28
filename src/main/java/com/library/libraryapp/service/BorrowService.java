package com.library.libraryapp.service;

import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.model.Member;
import com.library.libraryapp.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public List<Borrow> findBorrowsByMember(Member member) {
        return borrowRepository.findByMember(member);
    }
}
