package com.library.libraryapp.repository;


import com.library.libraryapp.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, String> {
}