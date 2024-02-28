package library;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class to represent an Author in the system, who can also be the Author of Books in the system.
 * 
 * @author Lauren Wilson
 */
public class Author {
    private String name;
    private final LocalDate dateOfBirth;
    private ArrayList<Book> bibliography;

    /**
     * Class constructor
     * 
     * @param name The name of the author
     * @param dateOfBirth The author's date of birth
     */
    public Author(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bibliography = new ArrayList<Book>();
    }

    /**
     * @return The name of the Author.
     *      */
    public String getName() { return name; }
    /**
     * @return The author's date of birth.
     */
    public LocalDate getDOB() { return dateOfBirth; }
    /**
     * @return A list of books written by the author. If the author has no works defined, the list will be empty.
     */
    public ArrayList<Book> getBibliography() { return bibliography; }

    /**
     * Sets the author's name
     * @param name The author's name.
     */
    // An author's birthday never changes, but their name might
    public void setName(String name) { this.name = name; }

    /**
     * Adds a Book to the list of this Author's works
     * @param book The Book to add.
     */
    // We also need to add an author's books to their bibliography
    public void addWork(Book book) { bibliography.add(book); }

    public String toString() {
        return String.format("%s (%d/%s/%d)\nBibliography: %d", name, dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
    }
}
