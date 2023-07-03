package com.library.libraryapp.controller;

import com.library.libraryapp.model.Library;
import com.library.libraryapp.service.LibraryService;
import com.library.libraryapp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final LibraryService libraryService; //Ajoutez le service de la bibliothèque

    @Autowired
    public MemberController(MemberService memberService, LibraryService libraryService) {
        this.memberService = memberService;
        this.libraryService = libraryService;
    }

    @PutMapping("/{memberId}/borrow/{documentId}/library/{libraryId}")
    public ResponseEntity<?> borrowDocument(@PathVariable Long memberId, @PathVariable Long documentId, @PathVariable Long libraryId) {
        try {
            Library library = libraryService.findById(libraryId); // Trouvez la bibliothèque par son ID
            memberService.borrowDocument(memberId, documentId, library);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Handle error properly in real application
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{memberId}/return/{documentId}/library/{libraryId}")
    public ResponseEntity<?> returnDocument(@PathVariable Long memberId, @PathVariable Long documentId, @PathVariable Long libraryId) {
        try {
            Library library = libraryService.findById(libraryId); // Trouvez la bibliothèque par son ID
            memberService.returnDocument(memberId, documentId, library);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Handle error properly in real application
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}