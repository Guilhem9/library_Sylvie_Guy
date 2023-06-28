package com.library.libraryapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Library extends HolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String libraryID;

    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Member> members;

//    private String name;
//    private ArrayList<String, Member> membersRegistered;
//    private HashMap<String, Librarian> librarianRegistered;
//    private HashMap<String, Borrow> currentBorrows = new HashMap<>();
//
//    // Constructor with name, documents held, documents possessed, and members registered
//    public Library(String name, ArrayList<Document> documentsHeld, ArrayList<Document> documentPossessed) {
//        super(documentsHeld, documentPossessed);
//        this.name = name;
//        this.membersRegistered = new HashMap<>();
//        this.librarianRegistered = new HashMap<>();
//    }
//
//    // Constructor with name, the most used
//    public Library(String name) {
//        super();
//        this.name = name;
//        this.membersRegistered = new HashMap<>();
//        this.librarianRegistered = new HashMap<>();
//    }
//
//    // Default constructor, just defines a default name
//    public Library() {
//        super();
//        this.name = "Unnamed Library";
//        this.membersRegistered = new HashMap<>();
//        this.librarianRegistered = new HashMap<>();
//    }
//
//    // Getter methods
//    public String getName() {
//        return this.name;
//    }
//
//    public String getLibraryID() {
//        return libraryID;
//    }
//
//    public HashMap<String, Member> getMembersRegistered() {
//        return this.membersRegistered;
//    }
//
//    public HashMap<String, Librarian> getLibrarianRegistered() {
//        return this.librarianRegistered;
//    }
//
//    public HashMap<String, Document> getDocumentHeldList() {
//        return this.getDocumentsHeld();
//    }
//
//    public HashMap<String, Borrow> getCurrentBorrows() {
//        return this.currentBorrows;
//    }
//
//    // Setter method for name
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void addLibrarian(Librarian librarian) {
//        librarianRegistered.put(librarian.getLibrarianID(), librarian);
//        System.out.println("\nLibrarian " + librarian.toString() + " successfully added to " + getName() + "!\n");
//    }
//
//    // Method to print the documents in the library
//    public void printDocuments() {
//        System.out.println("Documents dans la bibliothèque " + name);
//        for (Document document : this.getDocumentsHeld().values()) {
//            System.out.println("- Type: " + document.getType() + " , Titre: " + document.getTitle() + " , Auteur: " + document.getAuthor());
//        }
//    }
//
//    public HashMap<String, Document> getDocumentPossessedList() {
//        return this.getDocumentsPossessed();
//    }
}