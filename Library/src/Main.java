import javax.swing.plaf.synth.SynthUI;
import java.util.HashMap; // Importing the HashMap class
import java.time.Year; // Importing the java.time.Year class
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;



// Abstract class representing a document
abstract class Document {
    private String title;
    private String author;
    private Year releaseDate = Year.now();
    private Boolean isBorrowed = false;
    private HolderEntity possessor = null;
    private HolderEntity holder = null;
    private static int nextID = 0;
    private String documentID;
    private Borrow currentBorrow = null;
    private Member currentBorrower = null;





    // Constructor with title and author
    public Document(String title, String author) {
        this.title = title;
        this.author = author;
        this.documentID = "D" + String.valueOf(nextID++); // Generating a unique document ID

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


// Class representing a book, extends Document
class Book extends Document {
    // Constructors calling the corresponding Document constructors
    Book(String title, String author) {
        super(title, author);
    }

    Book(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    Book(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the book
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}


// Class representing a comics, extends Document
class Comics extends Document {
    // Constructors calling the corresponding Document constructors
    Comics(String title, String author) {
        super(title, author);
    }

    Comics(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    Comics(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the comics
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}


// Class representing an archive, extends Document
class Archive extends Document {
    // Constructors calling the corresponding Document constructors
    Archive(String title, String author) {
        super(title, author);
    }

    Archive(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    Archive(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the archive
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}


// Class representing a dictionary, extends Document
class Dictionary extends Document {
    // Constructors calling the corresponding Document constructors
    Dictionary(String title, String author) {
        super(title, author);
    }

    Dictionary(String title, String author, Year releaseDate) {
        super(title, author, releaseDate);
    }

    Dictionary(String title, String author, Year releaseDate, HolderEntity possessor, HolderEntity holder) {
        super(title, author, releaseDate, possessor, holder);
    }

    // Overriding the getType method to print and return the type of the dictionary
    @Override
    public String getType() {
        System.out.println("The type is " + this.getClass().getSimpleName());
        return this.getClass().getSimpleName();
    }
}


// Abstract class representing a holder entity (can be Readers or Libraries)
abstract class HolderEntity {
    private HashMap<String, Document> documentsHeld;
    private HashMap<String, Document> documentsPossessed;

    // Constructor with documents held and documents possessed
    public HolderEntity(HashMap<String, Document> documentsHeld, HashMap<String, Document> documentsPossessed) {
        this.documentsHeld = documentsHeld;
        this.documentsPossessed = documentsPossessed;
    }

    // Default constructor
    public HolderEntity() {
        this.documentsHeld = new HashMap<>();
        this.documentsPossessed = new HashMap<>();
    }

    // Getter methods for private fields
    public HashMap<String, Document> getDocumentsHeld() {
        return this.documentsHeld;
    }

    public HashMap<String, Document> getDocumentsPossessed() {
        return this.documentsPossessed;
    }
}


// Class representing a member, extends HolderEntity
class Member extends HolderEntity {
    private String lastName;
    private String firstName;
    private static int nextID = 0;
    private String memberID;
    private YearMonth registrationDate;
    private HashMap<String, Borrow> currentBorrows = new HashMap<>();
    private int membershipPoints = 100;

    // Constructor with last name, first name, documents held, and documents possessed
    public Member(String lastName, String firstName, HashMap<String, Document> documentsHeld, HashMap<String, Document> documentPossessed) {
        super(documentsHeld, documentPossessed);
        this.lastName = lastName;
        this.firstName = firstName;
        this.memberID = "M" + String.valueOf(nextID++);
        this.registrationDate = YearMonth.now();
    }

    // Constructor with last name and first name
    public Member(String lastName, String firstName) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.memberID = "M" + String.valueOf(nextID++);
        this.registrationDate = YearMonth.now();
    }

    // Getter methods for class Member
    public String getMemberID() {
        return memberID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public YearMonth getRegistrationDate() {
        return registrationDate;
    }
    public HashMap<String, Borrow> getCurrentBorrows() {
        return this.currentBorrows;
    }

    // Setter Methods for class Member
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setRegistrationDate(YearMonth registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Method to borrow a document
    public void borrowDocument(String documentID, Library library) throws Exception {
        // Check if member is registered in the library
        if (!library.getMembersRegistered().containsKey(memberID)) {
            throw new Exception("You are not a member of this Library" + library.getName() + ", please reach a Librarian to register.");
        }

        // Get the document from the library using the documentID
        Document document = library.getDocumentsHeld().get(documentID);

        // If the document is available, proceed with borrowing
        if (document == null) {
            throw new Exception("This " + document.getType() + " is not in " + library.getName());
        }

        // Create a new Borrow object with details of the borrowing
        Borrow borrow = new Borrow(this, document, library);

        // Update the library and member's current borrows list with the new borrow object
        library.getCurrentBorrows().put(borrow.getBorrowID(), borrow);
        this.getCurrentBorrows().put(borrow.getBorrowID(), borrow);

        // Update the library documents list
        library.getDocumentsHeld().remove(documentID);

        // Update the documents held by the member and the document's current borrower details
        this.getDocumentsHeld().put(document.getDocumentID(), document);
        document.setCurrentBorrower(this);
        document.setCurrentBorrow(borrow);
    }

    // Method to return a borrowed document to the library.
    public void returnDocument(String documentID, Library library) throws Exception {
        // Check if the member is registered in the library
        if (!library.getMembersRegistered().containsKey(memberID)) {
            throw new Exception("Error in the log in of member " + toString() + ", please reach a Librarian to solve the problem before returning the document");
        }

        // Check if the document to be returned is in the member's possession and belongs to the library
        if (!this.getDocumentsHeld().containsKey(documentID) || !library.getDocumentPossessedList().containsKey(documentID)) {
            throw new Exception("This document is not currently in the possession of " + toString() + "or doesn't belong to " + library.getName());
        }

        // Get the Document object from the library
        Document document = library.getDocumentsPossessed().get(documentID);

        // Get the Borrow object associated with the document
        Borrow borrow = document.getCurrentBorrow();

        if (borrow == null) {
            throw new Exception("No borrow found");
        }

        // Update the library and member's current borrows list by removing the borrow object
        library.getCurrentBorrows().remove(borrow.getBorrowID(), borrow);
        this.getCurrentBorrows().remove(borrow.getBorrowID(), borrow);

        // Update the documents held by the member and the library
        this.getDocumentsHeld().remove(documentID, document);
        library.getDocumentsHeld().put(documentID, document);

        // Update the document's holder details and borrowing status
        document.setHolder(library);
        document.setIsBorrowed(false);

        // If the return is late, deduct membership points
        if (borrow.isLate()) {
            int daysLate = borrow.daysLate();
            this.membershipPoints = membershipPoints - daysLate;
        }
    }

    @Override
    public String toString() {
        return "Type: " + getClass().getSimpleName() + " | First name: " + getFirstName() + " | Last name: " + getLastName() + " | Member since: " + getRegistrationDate();
    }
}


// Class representing a librarian, extends Member
class Librarian extends Member {
    private static int nextID = 0;
    private String librarianID;

    // Constructor with last name and first name
    public Librarian(String lastName, String firstName) {
        super(lastName, firstName);
        this.librarianID = String.valueOf(nextID++);


    }

    // Constructor with last name, first name, documents held, and documents possessed
    public Librarian(String lastName, String firstName, HashMap<String, Document> documentsHeld, HashMap<String, Document> documentPossessed) {
        super(lastName, firstName, documentsHeld, documentPossessed);
        this.librarianID = String.valueOf(nextID++);
    }

    // Getter method for librarian ID
    public String getLibrarianID() {
        return librarianID;
    }

    // Method to add a member to a library
    public void addMember(Member newMember, Library library) {
        if (library.getLibrarianRegistered().containsKey(getLibrarianID())) {
            library.getMembersRegistered().put(newMember.getMemberID(), newMember);
        }
        else {
            System.out.println("You are not a librarian from the library " + library.getName());
        }
    }

    public void removeMember(Member member, Library library) {
        if (library.getLibrarianRegistered().containsKey(getLibrarianID())) {
            if (library.getMembersRegistered().containsKey(member.getMemberID())) {
                library.getMembersRegistered().remove(member.getMemberID(), member);
            }
            else {
                System.out.println("This person is not registered as a member of the library " + library.getName());
            }
        }
        else {
            System.out.println("You are not a librarian from the library " + library.getName());
        }
    }

    // Method to add a document to a library
    public void addDocument(Document document, Library library) {
        if (library.getLibrarianRegistered().containsKey(getLibrarianID())) {
            if (document.isHeldBy(null)) {
                library.getDocumentsHeld().put(document.getDocumentID(), document);
                library.getDocumentsPossessed().put(document.getDocumentID(), document);
                document.setIsBorrowed(false);
                document.setPossessor(library);
                document.setHolder(library);
            }
            else {
                System.out.println("This document is already held by someone else");
            }
        }
        else {
        System.out.println("You are not a librarian from the library " + library.getName());
        }
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (Librarian ID:" + getLibrarianID() + ")";
    }
}

class Borrow {
    private Member member;
    private Document document;
    private Library library;
    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private static int nextBorrowID = 0;
    private String borrowID;


    public Borrow(Member member, Document document, Library library) {
        this.member = member;
        this.document = document;
        this.library = library;
        this.borrowDate = LocalDate.now();
        this.expectedReturnDate = borrowDate.plusDays(21);
        this.borrowID = "B" + String.valueOf(nextBorrowID++);
    }
    public Borrow(Member member, Document document, Library library, LocalDate borrowDate, LocalDate expectedReturnDate) {
        this(member,document, library);
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getBorrowID() {
        return borrowID;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public boolean isLate() {
        return actualReturnDate != null && expectedReturnDate.isBefore(actualReturnDate);
    }

    public Integer daysLate() {
        return (int) ChronoUnit.DAYS.between(expectedReturnDate, actualReturnDate);
    }

    public void setActualReturnDate(LocalDate date) {
        this.actualReturnDate = date;
    }

    public Document getDocument() {
        return document;
    }
}


// Class representing a library, extends HolderEntity
class Library extends HolderEntity {
    private String name;
    private HashMap<String, Member> membersRegistered;
    private HashMap<String, Librarian> librarianRegistered;
    private static int nextLibraryID = 0;
    private String libraryID;
    private HashMap<String, Borrow> currentBorrows = new HashMap<>();

    // Constructor with name, documents held, documents possessed, and members registered
    public Library(String name, HashMap<String, Document> documentsHeld, HashMap<String, Document> documentPossessed, HashMap<String, Member> membersRegistered) {
        super(documentsHeld, documentPossessed);
        this.name = name;
        this.membersRegistered = new HashMap<>();
        this.librarianRegistered = new HashMap<>();
        this.libraryID = "L" + String.valueOf(nextLibraryID++);
    }

    // Constructor with name, the most used
    public Library(String name) {
        super();
        this.name = name;
        this.membersRegistered = new HashMap<>();
        this.librarianRegistered = new HashMap<>();
    }

    // Default constructor, just defines a default name
    public Library() {
        super();
        this.name = "Unnamed Library";
        this.membersRegistered = new HashMap<>();
        this.librarianRegistered = new HashMap<>();
    }

    // Getter methods
    public String getName() {
        return this.name;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public HashMap<String, Member> getMembersRegistered() {
        return this.membersRegistered;
    }

    public HashMap<String, Librarian> getLibrarianRegistered() {
        return this.librarianRegistered;
    }

    public HashMap<String, Document> getDocumentHeldList() {
        return this.getDocumentsHeld();
    }

    public HashMap<String, Borrow> getCurrentBorrows() {
        return this.currentBorrows;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    public void addLibrarian(Librarian librarian) {
        librarianRegistered.put(librarian.getLibrarianID(), librarian);
        System.out.println("\nLibrarian " + librarian.toString() + " successfully added to " + getName() + "!\n");
    }

    // Method to print the documents in the library
    public void printDocuments() {
        System.out.println("Documents dans la bibliothèque " + name);
        for (Document document : this.getDocumentsHeld().values()) {
            System.out.println("- Type: " + document.getType() + " , Titre: " + document.getTitle() + " , Auteur: " + document.getAuthor());
        }
    }

    public HashMap<String, Document> getDocumentPossessedList() {
        return this.getDocumentsPossessed();
    }
}


    // Main class to run the program
public class Main {
    public static void main(String[] args) {

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
        Cesar.addMember(Cesar, MyLibrary); // Adding the librarian as a member of the library
        Member Guilhem = new Member("de Montbrun", "Guilhem"); // Creating a new member


        // Adding of the members into the library files and see the list of members
        Cesar.addMember(Guilhem, MyLibrary); // Adding the member to the library
        System.out.println((MyLibrary.getMembersRegistered()));


        // Adding of the documents in the library by the librarian
        Cesar.addDocument(Book1, MyLibrary); // Adding a document to the library
        Cesar.addDocument(Book2, MyLibrary);
        Cesar.addDocument(Comics1, MyLibrary);
        Cesar.addDocument(Archive1, MyLibrary);
        Cesar.addDocument(Dictionary1, MyLibrary);


        // Borrowing of books by members, and see the resulting document list of the library
        System.out.println("List of documents Held by the library : " + MyLibrary.getDocumentHeldList());
        try {
            Guilhem.borrowDocument(Book1.getDocumentID(), MyLibrary); // Member borrowing a document from the library
        } catch (Exception e) {
            e.printStackTrace();
        } // Member borrowing a document from the library
        System.out.println("List of documents held by  Guilhem : " + Guilhem.getDocumentsHeld());
        System.out.println("List of documents Held by the library afet the borrow : " + MyLibrary.getDocumentHeldList());


        // Print data from the borrow
        System.out.println(Guilhem.getCurrentBorrows().get("B0").getExpectedReturnDate());
        Guilhem.getCurrentBorrows().get("B0").setActualReturnDate(LocalDate.now());
        System.out.println(Guilhem.getCurrentBorrows().get("B0").isLate());


        // returning back the documents
        System.out.println(Guilhem.getDocumentsHeld());
        System.out.println(Guilhem.getCurrentBorrows());
        try {
            Guilhem.returnDocument(Book1.getDocumentID(), MyLibrary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Guilhem.getCurrentBorrows());


        // delete a member of the file system
        System.out.println();
        Cesar.removeMember(Guilhem, MyLibrary);
        MyLibrary.getMembersRegistered().toString();


        // try to borrow again a book
        try {
            Guilhem.borrowDocument(Comics1.getDocumentID(), MyLibrary); // Member borrowing a document from the library
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
*
*
 */