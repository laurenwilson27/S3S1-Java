package library;

import java.util.ArrayList;

/**
 * Class which represents a Patron of the library. The Patron can check books out to their borrowed list.
 */
public class Patron {
    private String name;
    private String address;
    private String phoneNum;
    private ArrayList<BookCopy> checkedOut;

    /**
     * Class constructor.
     * 
     * @param name Full name of the Patron.
     * @param address Home address of the Patron.
     * @param phoneNum Phone number of the Patron.
     */
    public Patron(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.checkedOut = new ArrayList<BookCopy>();
    }

    /**
     * 
     * @return The full name of the patron.
     */
    public String getName() { return name; }
    /**
     * 
     * @return The address of the patron.
     */
    public String getAddress() { return address; }
    /**
     * 
     * @return The phone number of the patron.
     */
    public String getPhoneNum() { return phoneNum; }
    /**
     * @return A list of books currently checked out by the patron.
     */
    public ArrayList<BookCopy> getCheckedOut() { return checkedOut; }

    /**
     * Sets the name of this patron.
     * 
     * @param name Name of the patron.
     */
    public void setName(String name) { this.name = name; }
    /**
     * Sets the address of this patron.
     * 
     * @param address Address of the patron.
     */
    public void setAddress(String address) { this.address = address; }
    /**
     * Sets the phone number of the patron.
     * 
     * @param phoneNum Phone number of the patron.
     */
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    // Functions to add or remove a copy of a book from the Patron's list of checked out books
    /**
     * Adds a specific BookCopy to this patron's list of checked out books.
     * 
     * @param book The copy to add.
     */
    public void addBook(BookCopy book) { checkedOut.add(book); }
    /**
     * Removes a specific BookCopy to this patron's list of checked out books.
     * 
     * @param book The copy to remove.
     */
    public void removeBook(BookCopy book) { checkedOut.remove(book); }

    /**
     * Returns the first checked out copy of a given book.
     * 
     * @param book The book to search for.
     * @return The matching BookCopy. If no match was found, value will be <code>null</code>.
     */
    public BookCopy findCopyOf(Book book) {
        BookCopy find = null;
        for (BookCopy copy : checkedOut) {
            if (copy.getBook() == book)
                find = copy;
            break;
        }

        return find;
    }

    /**
     * Returns all checked out copies of a given book.
     * 
     * @param book The book to search for.
     * @return A list of copies checked out by this patron.
     */
    public ArrayList<BookCopy> findCopiesOf(Book book) {
        ArrayList<BookCopy> results = new ArrayList<BookCopy>();
        for (BookCopy copy : checkedOut) {
            if (copy.getBook() == book)
                results.add(copy);
        }

        return results;
    }

    public String toString() {
        // Create a basic string for the Patron
        String fmt = String.format("Patron[name:'%s', address:'%s', phoneNum:'%s']",name, address, phoneNum);

        // Append a tidy list of books this Patron haschecked out
        fmt += "\nChecked out:\n";
        for (BookCopy copy : checkedOut)
            fmt += "\t" + copy.getBook().getTitle() + " <" + copy.getCheckoutDate() + ">";
        return fmt;
    }
}
