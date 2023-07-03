package com.library.libraryapp.service;
import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Library;
import com.library.libraryapp.model.Member;
import com.library.libraryapp.repository.BorrowRepository;
import com.library.libraryapp.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.libraryapp.repository.LibraryRepository;
import com.library.libraryapp.repository.MemberRepository;
import com.library.libraryapp.repository.DocumentRepository;


@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final MemberRepository memberRepository;
    private final BorrowRepository borrowRepository;
    private final DocumentRepository documentRepository;
    @Autowired
    public LibraryService(LibraryRepository libraryRepository, MemberRepository memberRepository, BorrowRepository borrowRepository, DocumentRepository documentRepository) {
        this.libraryRepository = libraryRepository;
        this.memberRepository = memberRepository;
        this.borrowRepository = borrowRepository;
        this.documentRepository = documentRepository;
    }

    public Library addMember(Long libraryId, Member member) {
        Library library = libraryRepository.findById(libraryId).orElseThrow(/* throw an exception */);
        member = memberRepository.save(member);  // save member to database and get the persisted entity
        library.getMembersRegistered().add(member);
        libraryRepository.save(library);  // save the library with the new member
        return library;
    }

    public void removeMember(Long memberId, Long libraryId) {
        Library library = libraryRepository.findById(libraryId).orElseThrow( /* throw an exception */ );
        Member member = memberRepository.findById(memberId).orElseThrow( /* throw an exception */ );

        if (borrowRepository.findByMemberId(memberId).isEmpty()) {
            library.getMembersRegistered().remove(member);
            memberRepository.delete(member);
            libraryRepository.save(library);
        } else {
            throw new IllegalArgumentException("The member still has borrowed documents");
        }
    }

    // Method to add a document to a library
    public void addDocument(long documentID, Long libraryId) {
        Document document = documentRepository.findById(documentID)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + documentID));
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        if (document.isHeldBy(null)) {
            library.getDocumentsHeld().add(document);
            library.getDocumentsPossessed().add(document);
            document.setIsBorrowed(false);
            document.setPossessor(library);
            document.setHolder(library);
        } else {
            System.out.println("This document is already held by someone else");
        }
        libraryRepository.save(library);
        documentRepository.save(document);
    }

    public Library findById(long libraryId){
        return libraryRepository.findById(libraryId).orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));
    }
    //removeDocument
    public void removeDocument(long documentID, Long libraryId) {
        Document document = documentRepository.findById(documentID)
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + documentID));
        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new RuntimeException("Library not found with id: " + libraryId));

        if (library.getDocumentsHeld().contains(document)) {
            library.getDocumentsHeld().remove(document);
            library.getDocumentsPossessed().remove(document);
            document.setIsBorrowed(false);
            document.setPossessor(null);
            document.setHolder(null);
        } else {
            System.out.println("This document is not in the specified library");
        }
        libraryRepository.save(library);
        documentRepository.save(document);
    }
}

