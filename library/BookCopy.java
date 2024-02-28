package library;

// A class for a physical, individual copy of a book

import java.time.LocalDate;

public class BookCopy implements Borrowable {
    // The enumerated status of the book
    private Status status;
    // A reference back to the book this is a copy of
    // This can't change - if the book changes, it's a different book
    // (Philosophers have been stumped by this one)
    private final Book copyOf;
    private Patron borrower;
    private LocalDate checkoutDate;

    public BookCopy(Book book) {
        this.copyOf = book;
        this.status = Status.AVAILABLE;
    }

    public Status getStatus() { return status; }
    public Book getBook() { return copyOf; }
    public Patron getBorrower() { return borrower; }
    public LocalDate getCheckoutDate() { return checkoutDate; }

    public void setStatus(Status status) { this.status = status; }
    public void setBorrower(Patron borrower) { this.borrower = borrower; }
    public void setCheckoutDate(LocalDate checkoutDate) { this.checkoutDate = checkoutDate; }

    // Borrowing a book adds this copy to the borrower's 'checked out' list
    @Override
    public void borrowCopy(Patron borrower) {
        setStatus(Status.CHECKED_OUT);
        setBorrower(borrower);
        setCheckoutDate(LocalDate.now());
        borrower.addBook(this);
    }

    // Returning a book makes this book AVAILABLE and removes it from the borrower's checked out list
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
