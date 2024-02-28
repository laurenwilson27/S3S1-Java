import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Library {
    private ArrayList<Author> authors;
    private ArrayList<Patron> patrons;
    private ArrayList<Book> books;

    // The Library's constructor has no parameters - authors, books, and patrons are added after the fact
    public Library() {
        this.authors = new ArrayList<Author>();
        this.patrons = new ArrayList<Patron>();
        this.books = new ArrayList<Book>();
    }

    public ArrayList<Author> getAuthors() { return authors; }
    public ArrayList<Patron> getPatrons() { return patrons; }
    public ArrayList<Book> getBooks() { return books; }

    public void addAuthor(Author author) { authors.add(author); }
    public void addPatron(Patron patron) { patrons.add(patron); }
    public void addBook(Book book) { books.add(book); }

    // Shortcut methods which create new instances of a class, add them to relevant a lists, and return the instances
    // This allows for simpler code elsewhere
    public Author addAuthor(String name, int year, int month, int day) {
        LocalDate newDOB = LocalDate.of(year, month, day);
        Author newAuthor = new Author(name, newDOB);

        authors.add(newAuthor);
        return newAuthor;
    }

    public Patron addPatron(String name, String address, String phoneNum) {
        Patron newPatron = new Patron(name, address, phoneNum);

        patrons.add(newPatron);
        return newPatron;
    }

    public Book addBook(String title, Author author, String isbn, String publisher) {
        Book newBook = new Book(title, author, isbn, publisher);
        books.add(newBook);

        return newBook;
    }

    // Function to search through known Authors for a specific name
    // returns null if no match was found
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

    // Searches for a book in the library by title
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

    // Searches for a book in the library by ISBN
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

    // Searches for books in the library by author
    // Since more than one book can have the same author, this returns a list of books
    public ArrayList<Book> findBooksByAuthor(String authorName) {
        ArrayList<Book> results = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName))
                results.add(book);
        }

        return results;
    } 

    // Searches for a library patron by name and address
    // (Because the name is not neccesarily unique, address is used as an additional filter)
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

    // Function to automatically add authors from a file
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

    // Function to automatically add authors from a file
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

    // Function to automatically add books from a file
    public void loadBooks(String file) {
        // We'll assume the file is formatted as CSV data, which is easy to parse
        String delimiter = ",";

        try {
            BufferedReader csv = new BufferedReader(new FileReader(file));
            String line;

            // Continue reading lines until there is nothing left to read
            while ((line = csv.readLine()) != null) {
                String data[] = line.split(delimiter);
                Book newBook = addBook(data[0], findAuthorByName(data[1]), data[2], data[3]);

            // Add a random number copies of each book
            int num = (int) (Math.random() * 5) + 2;
            newBook.addCopies(num);
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
