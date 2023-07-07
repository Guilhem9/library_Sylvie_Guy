package com.library.libraryapp.model;

import javax.persistence.DiscriminatorValue;
import java.time.Year;

// Class representing an Archive, extends Document
@DiscriminatorValue("Archive")
public class Archive extends Document {
    // Constructors calling the corresponding Document constructors
    public Archive(String title, String author) {
        super(title, author);
    }

    public Archive(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    public Archive(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the archive
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}