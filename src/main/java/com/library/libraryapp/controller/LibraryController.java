package com.library.libraryapp.controller;

import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Library;
import com.library.libraryapp.model.Member;
import com.library.libraryapp.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }
    @GetMapping("/members")
    public List<Member> getAllMembers(@RequestParam("libraryId") Long libraryId) {
        return libraryService.getAllMembers(libraryId);
    }

    @GetMapping("/members/{memberId}")
    public Member getMemberById(@PathVariable("memberId") Long memberId, @RequestParam("libraryId") Long libraryId) {
        return libraryService.getMemberById(memberId, libraryId);
    }

    @PostMapping("/members")
    public Library addMember(@RequestParam("libraryId") Long libraryId, @RequestBody Member member) {
        return libraryService.addMember(member, libraryId);
    }

    @PutMapping("/members/{memberId}")
    public Member updateMember(@PathVariable("memberId") Long memberId, @RequestParam("libraryId") Long libraryId, @RequestBody Member member) {
        return libraryService.updateMember(memberId, libraryId, member);
    }

    @DeleteMapping("/members/{memberId}")
    public void removeMember(@PathVariable("memberId") Long memberId, @RequestParam("libraryId") Long libraryId) {
        libraryService.removeMember(memberId, libraryId);
    }

    @GetMapping("/documents")
    public List<Document> getAllDocuments(@RequestParam("libraryId") Long libraryId) {
        return libraryService.getAllDocuments(libraryId);
    }

    @GetMapping("/documents/{documentId}")
    public Document getDocumentById(@PathVariable("documentId") Long documentId, @RequestParam("libraryId") Long libraryId) {
        return libraryService.getDocumentById(documentId, libraryId);
    }

    @PostMapping("/documents")
    public Document addDocument(@RequestParam("documentId") Long documentId, @RequestParam("libraryId") Long libraryId) {
        return libraryService.addDocument(documentId, libraryId);
    }

    @PutMapping("/documents/{documentId}")
    public Document updateDocument(@PathVariable("documentId") Long documentId, @RequestParam("libraryId") Long libraryId, @RequestBody Document document) {
        return libraryService.updateDocument(documentId, libraryId, document);
    }

    @DeleteMapping("/documents/{documentId}")
    public void removeDocument(@PathVariable("documentId") Long documentId, @RequestParam("libraryId") Long libraryId) {
        libraryService.removeDocument(documentId, libraryId);
    }
}
