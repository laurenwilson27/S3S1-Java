package library;

import java.util.ArrayList;

/**
 * Class which describes a book in the library.
 * It also contains a list of the physical copies of this book in the library.
 * 
 * @author Lauren Wilson
 */
public class Book {
    private String title;
    // I've decided that certain properties of a Book should never change
    private final Author author;
    private final String isbn;
    private String publisher;
    // Use an ArrayList to keep track of each available copy of a book in the library
    private ArrayList<BookCopy> copies;

    /**
     * Class constructor.
     * 
     * @param title Title of the book
     * @param author Author of the book
     * @param isbn ISBN of the book
     * @param publisher Publisher of the book
     */
    public Book(String title, Author author, String isbn, String publisher) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.copies = new ArrayList<BookCopy>();

        // This book should also be added to the author's bibliography
        this.author.addWork(this);
    }

    // Standard 'getters'
    /**
     * @return The book's title.
     */
    public String getTitle() { return title; }
    /**
     * @return The book's Author.
     */
    public Author getAuthor() { return author; }
    /**
     * @return The book's ISBN.
     */
    public String getISBN() { return isbn; }
    /**
     * @return The book's publisher.
     */
    public String getPublisher() { return publisher; }
    /**
     * @return A list of copies of this Book which exist in the Library.
     */
    public ArrayList<BookCopy> getCopies() { return copies;}

    /**
     * Adds a new copy of this book to the system.
     */
    public void addCopy() {
        BookCopy newCopy = new BookCopy(this);
        copies.add(newCopy);
    }

    /**
     * Adds a specified number of copies of this book.
     * 
     * @param num Number of copies to add.
     */
    public void addCopies(int num) { 
        for (int i = 0; i < num; i++)
            addCopy();
    }

    /**
     * Removes a specific copy of the book from the system. If the copy was checked out or unavailable, a warning is shown.
     * 
     * @param copy The specific BookCopy to be removed.
     */
    public void removeCopy(BookCopy copy) {
        // If the copy is unaccounted for, show an error
        if (copy.getStatus() != Status.AVAILABLE)
            System.out.println(String.format("WARNING: A checked-out copy of '%s' was removed from the system."));
        copies.remove(copy);
    }

    /**
     * Gets the number of copies of this book, regardless of their Status.
     * 
     * @return The number of copies of this book in the system.
     */
    public int getTotalCopies() { return copies.size(); }

    /**
     * Gets specifically the number of copies that are AVAILABLE.
     * 
     * @return The number of copies with a status of AVAILABLE.
     */
    public int getAvailableCopies() { 
        int total = 0;
        for (BookCopy copy : copies) {
            if (copy.getStatus() == Status.AVAILABLE)
                total ++;
        }
        return total;
    }

    /**
     * Gets the number of unavailable (CHECKED_OUT, OVERDUE) copies.
     * 
     * @return
     */
    public int getUnavailableCopies() { 
        int total = 0;
        for (BookCopy copy : copies) {
            if (copy.getStatus() != Status.AVAILABLE)
                total ++;
        }
        return total;
    }

    /**
     * Searches the list of copies for the first AVAILABLE copy.
     * 
     * @return An AVAILABLE copy of the book. If none were found, the value will be <code>null</code>.
     */
    public BookCopy nextAvailableCopy() {
        // Hopefully, this is only ever called after checking that there is an available copy!
        BookCopy lend = null;
        for (BookCopy copy : copies) {
            if (copy.getStatus() == Status.AVAILABLE) {
                lend = copy;
                break;
            }
        }

        return lend;
    }

    /**
     * Finds an AVAILABLE copy of the book and borrows it. If no copies are AVAILABLE, a message is displayed.
     * 
     * @param borrower The Patron borrowing a copy.
     */
    public void borrowCopy(Patron borrower) {
        boolean borrowed = false;
        for (BookCopy copy : copies) {
            if (copy.getStatus() == Status.AVAILABLE) {
                borrowed = true;
                copy.borrowCopy(borrower);
                break;
            }
        }

        if (!borrowed)
            System.out.println("Failed to borrow a copy of " + title + ", as there are none available;");
    }

    /**
     * Borrows a specific number of copies of a book. If there are not enough AVAILABLE copies, shows a message instead.
     * 
     * @param borrower The Patron who is borrowing this book.
     * @param num The number of copies being borrowed.
     */
    public void borrowCopies(Patron borrower, int num) {
        if (getAvailableCopies() >= num) {
        for  (int i = 0; i < num; i++) 
            borrowCopy(borrower);
        }
        else
        System.out.println("Could not borrow " + num + " copies of " + title + " beause only " + getAvailableCopies() + "are available.");
    }

    /**
     * Sets the title of the book.
     * 
     * @param title The title of this book.
     */
    public void setTitle(String title) { this.title = title; }
    /**
     * Sets the publisher of the book.
     * 
     * @param publisher The publisher of this book.
     */
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String toString() {
        String desc = "";
        desc += "Title:\t\t" + title + "\n";
        desc += "Author:\t\t" + author.getName() + "\n";
        desc += "ISBN:\t\t" + isbn + "\n";
        desc += "Publisher:\t" + publisher + "\n";
        desc += getTotalCopies() + " total copies, " + getAvailableCopies() + " available\n";
        desc += copies.toString();

        return desc;
    }
}