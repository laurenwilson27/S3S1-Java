package library;

import java.time.LocalDate;
import java.util.ArrayList;

public class Author {
    private String name;
    private final LocalDate dateOfBirth;
    private ArrayList<Book> bibliography;

    public Author(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.bibliography = new ArrayList<Book>();
    }

    public String getName() { return name; }
    public LocalDate getDOB() { return dateOfBirth; }
    public ArrayList<Book> getBibliography() { return bibliography; }

    // An author's birthday never changes, but their name might
    public void setName(String name) { this.name = name; }

    // We also need to add an author's books to their bibliography
    public void addWork(Book book) { bibliography.add(book); }

    public String toString() {
        return String.format("%s (%d/%s/%d)\nBibliography: %d", name, dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
    }
}
