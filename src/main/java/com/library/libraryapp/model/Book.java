package com.library.libraryapp.model;

import javax.persistence.DiscriminatorValue;
import java.time.Year;

// Class representing a book, extends Document
@DiscriminatorValue("Book")
public class Book extends Document {
    // Document constructors
    public Book(String title, String author) {
        super(title, author);
    }

    public Book(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    public Book(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the book
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}