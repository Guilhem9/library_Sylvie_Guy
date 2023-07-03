package com.library.libraryapp.model;

import javax.persistence.DiscriminatorValue;
import java.time.Year;

// Class representing a comics, extends Document
@DiscriminatorValue("COMICS")
public class Comics extends Document {
    // Constructors calling the corresponding Document constructors
    public Comics(String title, String author) {
        super(title, author);
    }

    public Comics(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    public Comics(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the comics
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}