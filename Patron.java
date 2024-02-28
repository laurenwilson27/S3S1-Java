import java.util.ArrayList;

public class Patron {
    private String name;
    private String address;
    private String phoneNum;
    private ArrayList<BookCopy> checkedOut;

    public Patron(String name, String address, String phoneNum) {
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.checkedOut = new ArrayList<BookCopy>();
    }

    // Basic getters and setters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNum() { return phoneNum; }
    public ArrayList<BookCopy> getCheckedOut() { return checkedOut; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    // Functions to add or remove a copy of a book from the Patron's list of checked out books
    public void addBook(BookCopy book) { checkedOut.add(book); }
    public void removeBook(BookCopy book) { checkedOut.remove(book); }

    // Returns the first checked out copy of a given book
    public BookCopy findCopyOf(Book book) {
        BookCopy find = null;
        for (BookCopy copy : checkedOut) {
            if (copy.getBook() == book)
                find = copy;
            break;
        }

        return find;
    }

    // Returns all checked out copies of a given Book
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
