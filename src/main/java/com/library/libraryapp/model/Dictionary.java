package com.library.libraryapp.model;


import javax.persistence.DiscriminatorValue;
import java.time.Year;

// Class representing a dictionary, extends Document
@DiscriminatorValue("Dictionary")
public class Dictionary extends Document {
    // Constructors calling the corresponding Document constructors
    public Dictionary(String title, String author) {
        super(title, author);
    }

    public Dictionary(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    public Dictionary(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the dictionary
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}