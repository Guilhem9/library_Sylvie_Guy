package com.library.libraryapp.model;

import java.time.Year;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import java.time.Year;

@Entity
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String documentID;

    private String title;
    private String author;
    private Year releaseDate = Year.now();
    private Boolean isBorrowed = false;

    @ManyToOne
    private HolderEntity possessor = null;

    @ManyToOne
    private HolderEntity holder = null;

    @ManyToOne
    private Borrow currentBorrow = null;

    @ManyToOne
    private Member currentBorrower = null;

    // Constructor with title and author
    public Document(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Constructor with title, author, and release date
    public Document(String title, String author, Year releaseDate) {
        this(title, author); // Calling the previous constructor
        this.releaseDate = releaseDate;
    }

    // Constructor with title, author, release date, possessor, and holder
    public Document(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        this(title, author, releaseDate); // Calling the previous constructor
        this.possessor = possessor;
        this.holder = holder;
    }

    // Getter methods for private fields
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Year getReleaseDate() {
        return releaseDate;
    }
    public HolderEntity getPossessor() {
        return possessor;
    }
    public HolderEntity getHolder() {
        return holder;
    }
    public String getDocumentID() {
        return documentID;
    }
    public Borrow getCurrentBorrow() {
        return currentBorrow;
    }
    public Member getCurrentBorrower() {
        return currentBorrower;
    }

    // Setter methods
    public void setReleaseDate(int releaseDate) {
        if (Year.of(releaseDate).isAfter(Year.now())) {
            System.out.println("Setting of the Release Year impossible: Year after current one");
        }
        else {
            this.releaseDate = Year.of(releaseDate);
        }
    }
    public void setHolder(HolderEntity newHolder) {
        this.holder = newHolder;
    }
    public void setPossessor(HolderEntity possessor) {
        this.possessor = possessor;
    }
    public void setIsBorrowed(Boolean borrowed) {
        this.isBorrowed = borrowed;
    }
    // Check if the document is held by a specific entity
    public boolean isHeldBy(HolderEntity entity) {
        return this.holder == entity;
    }
    // Abstract method to get the type of the document (to be implemented by subclasses)
    public abstract String getType();
    public void setCurrentBorrow(Borrow currentBorrow) {
        this.currentBorrow = currentBorrow;
    }
    public void setCurrentBorrower(Member currentBorrower) {
        this.currentBorrower = currentBorrower;
    }

    @Override
    public String toString() {
        return "Type: " + getClass().getSimpleName() + " | name: " + getTitle() + " | Author: " + getAuthor() + " | Release date: " + getReleaseDate();
    }
}
