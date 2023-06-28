package com.library.libraryapp.service;

import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.Library;
import org.springframework.beans.factory.annotation.Autowired;
import com.library.libraryapp.model.Member;
import com.library.libraryapp.repository.MemberRepository;

public class MemberService {
    private final DocumentService documentService;
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(DocumentService documentService, MemberRepository memberRepository){
        this.documentService = documentService;
        this.memberRepository = memberRepository;
    }


    public void borrowDocument(long memberID, long documentID, Library library) throws Exception {

        Member member = memberRepository.findByMemberID(memberID);

        // Check if member is registered in the library
        if (!library.getMembersRegistered().contains(member)) {
            throw new Exception("You are not a member of this Library" + library.getName() + ", please reach a Librarian to register.");
        }

        // Get the document from the library using the documentID
        Document document = documentService.findById(documentID);

        // If the document is available, proceed with borrowing
        if (document == null) {
            throw new Exception("This " + document.getType() + " is not in " + library.getName());
        }

        // Create a new Borrow object with details of the borrowing
        Borrow borrow = new Borrow(member, document, library);

        // Update the library and member's current borrows list with the new borrow object
        library.getCurrentBorrows().add(borrow);
        member.getCurrentBorrows().add(borrow);

        // Update the library documents list
        library.getDocumentsHeld().remove(documentID);

        // Update the documents held by the member and the document's current borrower details
        member.getDocumentsHeld().add(document);
        document.setCurrentBorrower(member);
        document.setCurrentBorrow(borrow);
    }

    // Method to return a borrowed document to the library.
    public void returnDocument(long memberID, long documentID, Library library) throws Exception {

        Member member = memberRepository.findByMemberID(memberID);
        Document document = documentService.findById(documentID);
        // Check if the member is registered in the library
        if (!library.getMembersRegistered().contains(member)) {
            throw new Exception("Error in the log in of member " + toString() + ", please reach a Librarian to solve the problem before returning the document");
        }

        // Check if the document to be returned is in the member's possession and belongs to the library
        if (!member.getDocumentsHeld().contains(document) || !library.getDocumentPossessedList().contains(document)) {
            throw new Exception("This document is not currently in the possession of " + toString() + "or doesn't belong to " + library.getName());
        }

        // Get the Document object from the library


        // Get the Borrow object associated with the document
        Borrow borrow = document.getCurrentBorrow();

        if (borrow == null) {
            throw new Exception("No borrow found");
        }

        // Update the library and member's current borrows list by removing the borrow object
        library.getCurrentBorrows().remove(borrow);
        member.getCurrentBorrows().remove(borrow);

        // Update the documents held by the member and the library
        member.getDocumentsHeld().remove(document);
        library.getDocumentsHeld().add(document);

        // Update the document's holder details and borrowing status
        document.setHolder(library);
        document.setIsBorrowed(false);

        // If the return is late, deduct membership points
        if (borrow.isLate()) {
            int daysLate = borrow.daysLate();
            member.setMembershipPoints(daysLate);
        }
    }
}
