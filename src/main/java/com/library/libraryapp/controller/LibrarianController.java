package com.library.libraryapp.controller;

import com.library.libraryapp.model.Library;
import com.library.libraryapp.service.LibraryService;
import com.library.libraryapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/librarians")
public class LibrarianController {

    private final MemberService memberService;
    private final LibraryService libraryService;

    @Autowired
    public LibrarianController(MemberService memberService, LibraryService libraryService) {
        this.memberService = memberService;
        this.libraryService = libraryService;
    }

    // Les mêmes méthodes que dans MemberController
    @PutMapping("/{librarianId}/borrow/{documentId}/library/{libraryId}")
    public ResponseEntity<?> borrowDocument(@PathVariable Long librarianId, @PathVariable Long documentId, @PathVariable Long libraryId) {
        Library library = libraryService.findById(libraryId);
        try {
            memberService.borrowDocument(librarianId, documentId, library);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{librarianId}/return/{documentId}/library/{libraryId}")
    public ResponseEntity<?> returnDocument(@PathVariable Long librarianId, @PathVariable Long documentId, @PathVariable Long libraryId) {
        Library library = libraryService.findById(libraryId);
        try {
            memberService.returnDocument(librarianId, documentId, library);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Les méthodes spécifiques à Librarian
    // ...
}