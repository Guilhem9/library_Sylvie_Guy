package com.library.libraryapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public
class Librarian extends Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "librarian_id")
    private long librarianID;

    // Constructor with last name and first name
    public Librarian(String lastName, String firstName) {
        super(lastName, firstName);
    }

    // Constructor with last name, first name, documents held, and documents possessed
    public Librarian(String lastName, String firstName, List<Document> documentsHeld, List<Document> documentPossessed) {
        super(lastName, firstName, documentsHeld, documentPossessed);
    }

    // Getter method for librarian ID
    public long getLibrarianID() {
        return librarianID;
    }

    // Method to add a member to a library


    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Librarian ID:" + getLibrarianID() + ")";
    }
}
