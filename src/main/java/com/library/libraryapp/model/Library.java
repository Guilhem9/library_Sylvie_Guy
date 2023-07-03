package com.library.libraryapp.model;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Library extends HolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long libraryId;
    private String name;
    @OneToMany(mappedBy = "library")
    private List<Member> membersRegistered;
    @OneToMany(mappedBy = "library")
    private List<Librarian> librarianRegistered;
    @OneToMany(mappedBy = "library")
    private List<Borrow> currentBorrows = new ArrayList<>();

    // Constructor with name, documents held, documents possessed, and members registered
    public Library(String name, List<Document> documentsHeld, List<Document> documentPossessed) {
        super(documentsHeld, documentPossessed);
        this.name = name;
        this.membersRegistered = new ArrayList<>();
        this.librarianRegistered = new ArrayList<>();
    }

    // Constructor with name, the most used
    public Library(String name) {
        super();
        this.name = name;
        this.membersRegistered = new ArrayList<>();
        this.librarianRegistered = new ArrayList<>();
    }

    // Default constructor, just defines a default name
    public Library() {
        super();
        this.name = "Unnamed Library";
        this.membersRegistered = new ArrayList<>();
        this.librarianRegistered = new ArrayList<>();
    }

    // Getter methods
    public String getName() {
        return this.name;
    }

    public long getLibraryId() {
        return this.libraryId;
    }

    public List<Member> getMembersRegistered() {
        return this.membersRegistered;
    }

    public List<Librarian> getLibrarianRegistered() {
        return this.librarianRegistered;
    }

    public List<Document> getDocumentHeld() {
        return this.getDocumentsHeld();
    }

    public List<Borrow> getCurrentBorrows() {
        return this.currentBorrows;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    public void addLibrarian(Librarian librarian) {
        librarianRegistered.add(librarian);
        System.out.println("\nLibrarian " + librarian.toString() + " successfully added to " + getName() + "!\n");
    }

    // Method to print the documents in the library
    public void printDocuments() {
        System.out.println("Documents dans la bibliothèque " + name);
        for (Document document : this.getDocumentsHeld()) {
            System.out.println("- Type: " + document.getType() + " , Titre: " + document.getTitle() + " , Auteur: " + document.getAuthor());
        }
    }

    public List<Document> getDocumentPossessedList() {
        return this.getDocumentsPossessed();
    }
}