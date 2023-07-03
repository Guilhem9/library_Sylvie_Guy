package com.library.libraryapp.repository;


import com.library.libraryapp.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}