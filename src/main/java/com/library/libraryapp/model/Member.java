package com.library.libraryapp.model;

import javax.persistence.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends HolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberID;

    private String lastName;
    private String firstName;
    private YearMonth registrationDate;
    private List<Borrow> currentBorrows = new ArrayList<>();
    private int membershipPoints = 100;

    // Constructor with last name, first name, documents held, and documents possessed
    public Member(String lastName, String firstName, List<Document> documentsHeld, List<Document> documentPossessed) {
        super(documentsHeld, documentPossessed);
        this.lastName = lastName;
        this.firstName = firstName;
        this.registrationDate = YearMonth.now();
    }

    // Constructor with last name and first name
    public Member(String lastName, String firstName) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.registrationDate = YearMonth.now();
    }

    // Getter methods for class Member
    public Long getMemberID() {
        return memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public YearMonth getRegistrationDate() {
        return registrationDate;
    }

    public List<Borrow> getCurrentBorrows() {
        return this.currentBorrows;
    }

    public int getMembershipPoints(){
        return membershipPoints;
    }

    // Setter Methods for class Member
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRegistrationDate(YearMonth registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setMembershipPoints(int daysLate) {
        if (membershipPoints-daysLate >= 0) {
            this.membershipPoints -= daysLate;
        }
        else {
            System.out.println("Membership points equals to 0 or less. Member " + memberID + " shall be removed");
        }
    }
}
/*
public class Member extends HolderEntity {
    private String lastName;
    private String firstName;
    private static int nextID = 0;
    private String memberID;
    private YearMonth registrationDate;
    private HashMap<String, Borrow> currentBorrows = new HashMap<>();
    private int membershipPoints = 100;

    // Constructor with last name, first name, documents held, and documents possessed
    public Member(String lastName, String firstName, HashMap<String, Document> documentsHeld, HashMap<String, Document> documentPossessed) {
        super(documentsHeld, documentPossessed);
        this.lastName = lastName;
        this.firstName = firstName;
        this.memberID = "M" + String.valueOf(nextID++);
        this.registrationDate = YearMonth.now();
    }

    // Constructor with last name and first name
    public Member(String lastName, String firstName) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.memberID = "M" + String.valueOf(nextID++);
        this.registrationDate = YearMonth.now();
    }

    // Getter methods for class Member
    public String getMemberID() {
        return memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public YearMonth getRegistrationDate() {
        return registrationDate;
    }

    public HashMap<String, Borrow> getCurrentBorrows() {
        return this.currentBorrows;
    }

    // Setter Methods for class Member
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRegistrationDate(YearMonth registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Method to borrow a document
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

    @Override
    public String toString() {
        return "Type: " + getClass().getSimpleName() + " | First name: " + getFirstName() + " | Last name: " + getLastName() + " | Member since: " + getRegistrationDate();
    }
}
*/
