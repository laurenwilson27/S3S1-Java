import java.util.ArrayList;

public class Book {
    private String title;
    // I've decided that certain properties of a Book should never change
    private final Author author;
    private final String isbn;
    private String publisher;
    // Use an ArrayList to keep track of each available copy of a book in the library
    private ArrayList<BookCopy> copies;

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
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public String getISBN() { return isbn; }
    public String getPublisher() { return publisher; }
    public ArrayList<BookCopy> getCopies() { return copies;}

    // Adds a new copy of this book to the library
    public void addCopy() {
        BookCopy newCopy = new BookCopy(this);
        copies.add(newCopy);
    }

    // Shortcut to do the above multiple times
    public void addCopies(int num) { 
        for (int i = 0; i < num; i++)
            addCopy();
    }

    // Remove a specific copy from the system
    public void removeCopy(BookCopy copy) {
        // If the copy is unaccounted for, show an error
        if (copy.getStatus() != Status.AVAILABLE)
            System.out.println(String.format("WARNING: A checked-out copy of '%s' was removed from the system."));
        copies.remove(copy);
    }

    // Returns the number of copies of this book, regardless of their Status
    public int getTotalCopies() { return copies.size(); }

    // Returns specifically the number of copies that are AVAILABLE
    public int getAvailableCopies() { 
        int total = 0;
        for (BookCopy copy : copies) {
            if (copy.getStatus() == Status.AVAILABLE)
                total ++;
        }
        return total;
    }

    // Just in case - get the number of unavailable (CHECKED_OUT, OVERDUE) copies
    public int getUnavailableCopies() { 
        int total = 0;
        for (BookCopy copy : copies) {
            if (copy.getStatus() != Status.AVAILABLE)
                total ++;
        }
        return total;
    }

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

    public void borrowCopies(Patron borrower, int num) {
        if (getAvailableCopies() >= num) {
        for  (int i = 0; i < num; i++) 
            borrowCopy(borrower);
        }
        else
        System.out.println("Could not borrow " + num + " copies of " + title + " beause only " + getAvailableCopies() + "are available.");
    }

    // Standard 'setters'
    public void setTitle(String title) { this.title = title; }
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