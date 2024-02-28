package library;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Class to represent the full library system.
 * 
 * @author Lauren Wilson
 */
public class Library {
    private ArrayList<Author> authors;
    private ArrayList<Patron> patrons;
    private ArrayList<Book> books;

    /**
     * Class constructor. Once instantiated, Authors, Patrons, and Books can be added to the Library.
     */
    // The Library's constructor has no parameters - authors, books, and patrons are added after the fact
    public Library() {
        this.authors = new ArrayList<Author>();
        this.patrons = new ArrayList<Patron>();
        this.books = new ArrayList<Book>();
    }

    /**
     * 
     * @return A list of the Authors known by the library. Every Book in the system must be written by one of these Authors.
     */
    public ArrayList<Author> getAuthors() { return authors; }
    /**
     * 
     * @return A list of Patrons registered in the library.
     */
    public ArrayList<Patron> getPatrons() { return patrons; }
    /**
     * 
     * @return A list of Books which can be found in the library.
     */
    public ArrayList<Book> getBooks() { return books; }

    /**
     * Adds an author to the system.
     * 
     * @param author The Author to be added.
     */
    public void addAuthor(Author author) { authors.add(author); }
    /**
     * Adds a patron to the system.
     * 
     * @param patron The Patron to be added.
     */
    public void addPatron(Patron patron) { patrons.add(patron); }
    /**
     * Adds a book to the system. Note that copies of it are not automatically made available.
     * 
     * @param book The Book to be added
     */
    public void addBook(Book book) { books.add(book); }

    // Shortcut methods which create new instances of a class, add them to relevant a lists, and return the instances
    // This allows for simpler code elsewhere
    /**
     * Adds a new Author to the system, and returns the Author.
     * 
     * @param name Name of the author.
     * @param year Year the author was born.
     * @param month Month the author was born.
     * @param day Day the author was born.
     * @return The new Author.
     */
    public Author addAuthor(String name, int year, int month, int day) {
        LocalDate newDOB = LocalDate.of(year, month, day);
        Author newAuthor = new Author(name, newDOB);

        authors.add(newAuthor);
        return newAuthor;
    }

    /**
     * Adds a new Patron to the system, and retrns the Patron.
     * 
     * @param name Name of the patron.
     * @param address Home address of the patron.
     * @param phoneNum Phone number of the patron.
     * @return The new Patron.
     */
    public Patron addPatron(String name, String address, String phoneNum) {
        Patron newPatron = new Patron(name, address, phoneNum);

        patrons.add(newPatron);
        return newPatron;
    }

    /**
     * Adds a new Book to the system, and returns the Book.
     * 
     * @param title Title of the book.
     * @param author Author of the book.
     * @param isbn ISBN of the book.
     * @param publisher Publisher of the book.
     * @return The new Book
     */
    public Book addBook(String title, Author author, String isbn, String publisher) {
        Book newBook = new Book(title, author, isbn, publisher);
        books.add(newBook);

        return newBook;
    }

    /**
     * Searches known Authors for a specific name.
     * 
     * @param name Name to search for.
     * @return The first matching Author. Will be <code>null</code> if no match was found.
     */
    public Author findAuthorByName(String name) {
        Author result = null;
        for (Author author : authors) {

            if (author.getName().equalsIgnoreCase(name)) {
                result = author;
                break;
            }
        }

        return result;
    }

    /**
     * Searches for a Book with the given title.
     * 
     * @param title Title of the book to search for.
     * @return The first matching Book. Will be <code>null</code> if no match was found.
     */
    public Book findBookByTitle(String title) {
        Book result = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result = book;
                break;
            }
        }

        return result;
    }

    /**
     * Searches for a Book with the given ISBN.
     * 
     * @param isbn The ISBN of the book.
     * @return The first matching Book. Will be <code>null</code> if no match was found.
     */
    public Book findBookByISBN(String isbn) {
        Book result = null;
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(isbn)) {
                result = book;
                break;
            }
        }

        return result;
    }
    /**
     * Searches for all Books by the given author.
     * 
     * @param authorName The name of the book's author.
     * @return A list of all matching Books.
     */
    public ArrayList<Book> findBooksByAuthor(String authorName) {
        ArrayList<Book> results = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName))
                results.add(book);
        }

        return results;
    } 

    /**
     * Searches for a Patron of the library. Both their name and home address must be specified.
     * 
     * @param name Name of the Patron.
     * @param address Address of the Patron.
     * @return The matching Matron. Will be <code>null</code> if no match was found.
     */
    public Patron findPatron(String name, String address) {
        Patron result = null;

        // First, make a list of patrons with a matching name
        ArrayList<Patron> filteredPatrons = new ArrayList<Patron>();
        for (Patron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name))
                filteredPatrons.add(patron);    
        }

        // If the list is not empty (patrons were found), look for a matching address next
        if (filteredPatrons.size() > 0) {
            for (Patron patron : filteredPatrons) {
                if (patron.getAddress().equalsIgnoreCase(address)) {
                    result = patron;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Reads a list of Authors from a file. The list must be in CSV format.
     * 
     * @param file The file to read.
     */
    public void loadAuthors(String file) {
        // We'll assume the file is formatted as CSV data, which is easy to parse
        String delimiter = ",";

        try {
            BufferedReader csv = new BufferedReader(new FileReader(file));
            String line;

            // Continue reading lines until there is nothing left to read
            while ((line = csv.readLine()) != null) {
                String data[] = line.split(delimiter);
                addAuthor(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            }

            csv.close();

            System.out.println("Authors loaded from " + file);

        } catch (IOException e) {
            System.err.println("There was an error reading " + file + ":");
            e.printStackTrace();
        };
    }

    /**
     * Reads a list of Patrons from a file. The list must be in CSV format.
     * 
     * @param file The file to read.
     */
    public void loadPatrons(String file) {
        // We'll assume the file is formatted as CSV data, which is easy to parse
        String delimiter = ",";

        try {
            BufferedReader csv = new BufferedReader(new FileReader(file));
            String line;

            // Continue reading lines until there is nothing left to read
            while ((line = csv.readLine()) != null) {
                String data[] = line.split(delimiter);
                addPatron(data[0], data[1], data[2]);
            }

            csv.close();

            System.out.println("Patrons loaded from " + file);

        } catch (IOException e) {
            System.err.println("There was an error reading " + file + ":");
            e.printStackTrace();
        };
    }

    /**
     * Reads a list of Books from a file. The list must be in CSV format.
     * 
     * @param file The file to read.
     */
    public void loadBooks(String file) {
        // We'll assume the file is formatted as CSV data, which is easy to parse
        String delimiter = ",";

        try {
            BufferedReader csv = new BufferedReader(new FileReader(file));
            String line;

            // Continue reading lines until there is nothing left to read
            while ((line = csv.readLine()) != null) {
                String data[] = line.split(delimiter);
                addBook(data[0], findAuthorByName(data[1]), data[2], data[3]);
            }

            csv.close();

            System.out.println("Books loaded from " + file);

        } catch (IOException e) {
            System.err.println("There was an error reading " + file + ":");
            e.printStackTrace();
        };
    }

    public String toString() {
        String output = "Books in this Library:\n";
        for (Book book : books)
            output += book.toString() + "\n\n";

        return output;
    }
}
