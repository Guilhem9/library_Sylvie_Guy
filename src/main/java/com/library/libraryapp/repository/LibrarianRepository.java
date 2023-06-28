package com.library.libraryapp.repository;

import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}
