package com.library.libraryapp.repository;
import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
