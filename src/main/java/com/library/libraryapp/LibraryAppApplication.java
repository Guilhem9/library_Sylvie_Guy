package com.library.libraryapp;

import com.library.libraryapp.service.LibraryService;
import com.library.libraryapp.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.library.libraryapp.model.Archive;
import com.library.libraryapp.model.Book;
import com.library.libraryapp.model.Comics;
import com.library.libraryapp.model.Dictionary;
import com.library.libraryapp.model.Library;
import com.library.libraryapp.model.Librarian;
import com.library.libraryapp.model.Member;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.Year;

@EntityScan(basePackages = "com.library.libraryapp.*")
@SpringBootApplication
public class LibraryAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryAppApplication.class, args);
		ApplicationContext context = SpringApplication.run(LibraryAppApplication.class, args);

		LibraryService libraryService = context.getBean(LibraryService.class);
		MemberService memberService = context.getBean(MemberService.class);
		/*
		 * The goal of this main will be to cover all the use cases defined by the class as follows:
		 * -creation of books, comics, dictionaries and archives
		 * -modification of a document's data
		 * -creation of a library
		 * -creation of members and librarians
		 * -adding of the librarian and the members into the library files
		 * -adding of the documents in the library by the librarian
		 * -borrowing of books by members, and see the resulting document list of the library
		 * -print data from the borrow
		 * -returning back the documents
		 * -delete a member of the file system
		 * -try to borrow without being in the library systems
		 * */


		// Creation of books, comics, dictionaries and archives
		Book Book1 = new Book("Harry Potter", "J.K Rowling", Year.of(2000)); // Creating a new book
		Book Book2 = new Book("Le Seigneur des Anneaux", "Tolkien");
		Comics Comics1 = new Comics("SpiderMan Vol:1", "Stan Lee");
		Archive Archive1 = new Archive("Birth Acts of 2022", "Unknown");
		Dictionary Dictionary1 = new Dictionary("The Larousse", "Larousse Editions");


		// Modification of a document's data
		Book1.setReleaseDate(2004);
		System.out.println(Book1.getReleaseDate());
		System.out.println(Book1.toString());


		// Creation of a new library
		Library MyLibrary = new Library();


		// Creation of members and librarians
		Librarian Cesar = new Librarian("Pigeart de Gurbert", "César"); // Creating a new librarian
		MyLibrary.addLibrarian(Cesar);
		libraryService.addMember(Cesar, MyLibrary.getId()); // Adding the librarian as a member of the library
		Member Guilhem = new Member("de Montbrun", "Guilhem"); // Creating a new member


		// Adding of the members into the library files and see the list of members
		libraryService.addMember(Guilhem, MyLibrary.getLibraryID()); // Adding the member to the library
		System.out.println((MyLibrary.getMembersRegistered()));


		// Adding of the documents in the library by the librarian
		libraryService.addDocument(Book1.getDocumentID(), MyLibrary.getLibraryID()); // Adding a document to the library
		libraryService.addDocument(Book2.getDocumentID(), MyLibrary.getLibraryID());
		libraryService.addDocument(Comics1.getDocumentID(), MyLibrary.getLibraryID());
		libraryService.addDocument(Archive1.getDocumentID(), MyLibrary.getLibraryID());
		libraryService.addDocument(Dictionary1.getDocumentID(), MyLibrary.getLibraryID());


		// Borrowing of books by members, and see the resulting document list of the library
		System.out.println("List of documents Held by the library : " + MyLibrary.getDocumentsHeld());
		try {
			memberService.borrowDocument(Guilhem.getId(), Book1.getDocumentID(), MyLibrary); // Member borrowing a document from the library
		} catch (Exception e) {
			e.printStackTrace();
		} // Member borrowing a document from the library
		System.out.println("List of documents held by  Guilhem : " + Guilhem.getDocumentsHeld());
		System.out.println("List of documents Held by the library afet the borrow : " + MyLibrary.getDocumentsHeld());



		// returning back the documents
		System.out.println(Guilhem.getDocumentsHeld());
		System.out.println(Guilhem.getCurrentBorrows());
		try {
			memberService.returnDocument(Guilhem.getId(), Book1.getDocumentID(), MyLibrary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Guilhem.getCurrentBorrows());


		// delete a member of the file system
		System.out.println();
		libraryService.removeMember(Guilhem.getId(), MyLibrary.getLibraryID());
		MyLibrary.getMembersRegistered().toString();


		// try to borrow again a book
		try {
			memberService.borrowDocument(Guilhem.getId(), Comics1.getDocumentID(), MyLibrary); // Member borrowing a document from the library
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


