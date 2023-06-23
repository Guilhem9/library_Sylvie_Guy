package com.library.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import com.library.libraryapp.model.Archive;
import com.library.libraryapp.model.Borrow;
import com.library.libraryapp.model.Book;
import com.library.libraryapp.model.Comics;
import com.library.libraryapp.model.Dictionnary;
import com.library.libraryapp.model.Document;
import com.library.libraryapp.model.HolderEntity;
import com.library.libraryapp.model.Library;
import com.library.libraryapp.model.Librarian;
import com.library.libraryapp.model.Member;

import java.util.List;

@SpringBootApplication
public class LibraryAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryAppApplication.class, args);
	}

}
