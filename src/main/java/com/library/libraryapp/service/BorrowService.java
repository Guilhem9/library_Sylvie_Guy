package com.library.libraryapp.service;

import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(String id) {
        return borrowRepository.findById(id).orElse(null);
    }
}
