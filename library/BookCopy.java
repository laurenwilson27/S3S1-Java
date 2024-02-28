package library;

import java.time.LocalDate;

/**
 * A class representing a physical copy of a Book in the Library.
 * 
 * @author Lauren Wilson
 */
public class BookCopy implements Borrowable {
    // The enumerated status of the book
    private Status status;
    // A reference back to the book this is a copy of
    // This can't change - if the book changes, it's a different book
    // (Philosophers have been stumped by this one)
    private final Book copyOf;
    private Patron borrower;
    private LocalDate checkoutDate;

    /**
     * Class constructor. The status of the new copy is set to AVAILABLE.
     * 
     * @param book The Book that this is a copy of.
     */
    public BookCopy(Book book) {
        this.copyOf = book;
        this.status = Status.AVAILABLE;
    }

    /**
     * @return The enumerated status of this copy.
     */
    public Status getStatus() { return status; }
    /**
     * @return The Book which this is a copy of.
     */
    public Book getBook() { return copyOf; }
    /**
     * @return The Patron which has borrowed this copy.
     */
    public Patron getBorrower() { return borrower; }
    /**
     * @return The date this copy was checked out.
     */
    public LocalDate getCheckoutDate() { return checkoutDate; }

    /**
     * Sets the status of this copy.
     * 
     * @param status The status of this book. Must be a value from the Status enum.
     */
    public void setStatus(Status status) { this.status = status; }
    /**
     * Sets the borrower of this copy.
     * 
     * @param borrower The Patron who is borrowing this book.
     */
    public void setBorrower(Patron borrower) { this.borrower = borrower; }
    /**
     * Sets the date this copy was checked out.
     * 
     * @param checkoutDate The date of the checkout.
     */
    public void setCheckoutDate(LocalDate checkoutDate) { this.checkoutDate = checkoutDate; }

    /**
     * Borrows this copy of the book - sets the status to CHECKED_OUT, sets the checkout date to the current date, and adds this copy to the borrower's list of checked out books.
     * 
     * @param borrower The Patron who is borrowing this book.
     */
    // Borrowing a book adds this copy to the borrower's 'checked out' list
    @Override
    public void borrowCopy(Patron borrower) {
        setStatus(Status.CHECKED_OUT);
        setBorrower(borrower);
        setCheckoutDate(LocalDate.now());
        borrower.addBook(this);
    }

    /**
     * Returns this copy of the book, making it AVAILABLE again.
     */
    @Override
    public void returnCopy() {
        setStatus(Status.AVAILABLE);
        borrower.removeBook(this);
        setBorrower(null);
    }

    public String toString() { 
        // (Breaks are not needed for this switch statement, as returns function as breaks)
        switch (status) {
            case AVAILABLE:
                return "AVAILABLE";
            case CHECKED_OUT:
                return "CHECKED_OUT <" + getCheckoutDate() + ">";
            case OVERDUE:
                return "OVERDUE";
            default:
                return "<unknown>";
        }
    }
}
