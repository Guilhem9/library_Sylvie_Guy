package com.library.libraryapp.service;

import com.library.libraryapp.model.Document;
import com.library.libraryapp.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(String id) {
        return documentRepository.findById(id).orElse(null);
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }

    public void deleteDocument(String id) {
        documentRepository.deleteById(id);
    }

    public void borrowDocument(String documentID, Library library) throws Exception {
        // Check if member is registered in the library
        if (!library.getMembersRegistered().containsKey(memberID)) {
            throw new Exception("You are not a member of this Library" + library.getName() + ", please reach a Librarian to register.");
        }

        // Get the document from the library using the documentID
        Document document = library.getDocumentsHeld().get(documentID);

        // If the document is available, proceed with borrowing
        if (document == null) {
            throw new Exception("This " + document.getType() + " is not in " + library.getName());
        }

        // Create a new Borrow object with details of the borrowing
        Borrow borrow = new Borrow(this, document, library);

        // Update the library and member's current borrows list with the new borrow object
        library.getCurrentBorrows().put(borrow.getBorrowID(), borrow);
        this.getCurrentBorrows().put(borrow.getBorrowID(), borrow);

        // Update the library documents list
        library.getDocumentsHeld().remove(documentID);

        // Update the documents held by the member and the document's current borrower details
        this.getDocumentsHeld().put(document.getDocumentID(), document);
        document.setCurrentBorrower(this);
        document.setCurrentBorrow(borrow);
    }

    // Method to return a borrowed document to the library.
    public void returnDocument(String documentID, Library library) throws Exception {
        // Check if the member is registered in the library
        if (!library.getMembersRegistered().containsKey(memberID)) {
            throw new Exception("Error in the log in of member " + toString() + ", please reach a Librarian to solve the problem before returning the document");
        }

        // Check if the document to be returned is in the member's possession and belongs to the library
        if (!this.getDocumentsHeld().containsKey(documentID) || !library.getDocumentPossessedList().containsKey(documentID)) {
            throw new Exception("This document is not currently in the possession of " + toString() + "or doesn't belong to " + library.getName());
        }

        // Get the Document object from the library
        Document document = library.getDocumentsPossessed().get(documentID);

        // Get the Borrow object associated with the document
        Borrow borrow = document.getCurrentBorrow();

        if (borrow == null) {
            throw new Exception("No borrow found");
        }

        // Update the library and member's current borrows list by removing the borrow object
        library.getCurrentBorrows().remove(borrow.getBorrowID(), borrow);
        this.getCurrentBorrows().remove(borrow.getBorrowID(), borrow);

        // Update the documents held by the member and the library
        this.getDocumentsHeld().remove(documentID, document);
        library.getDocumentsHeld().put(documentID, document);

        // Update the document's holder details and borrowing status
        document.setHolder(library);
        document.setIsBorrowed(false);

        // If the return is late, deduct membership points
        if (borrow.isLate()) {
            int daysLate = borrow.daysLate();
            this.membershipPoints = membershipPoints - daysLate;
        }
    }
}