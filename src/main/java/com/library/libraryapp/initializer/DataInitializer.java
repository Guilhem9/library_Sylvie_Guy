package com.library.libraryapp.initializer;

import com.library.libraryapp.model.Library;
import com.library.libraryapp.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final LibraryService libraryService;

    @Autowired
    public DataInitializer(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostConstruct
    public void init() {
        Library library = new Library();
        // configure the library object
        libraryService.saveLibrary(library);
    }
}