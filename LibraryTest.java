import java.util.Scanner;

import library.Book;
import library.Library;
import library.Patron;

public class LibraryTest {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner input = new Scanner(System.in);

        lib.loadAuthors("./data/authors.csv");
        lib.loadPatrons("./data/patrons.csv");
        lib.loadBooks("./data/books.csv");

        // Add a random number (3-7) of copies of each book, so we can test borrowing
        for (Book book : lib.getBooks()) {
            int num = (int) (Math.random() * 5) + 3;
            book.addCopies(num);
        }

        System.out.println("Get ISBN of The Big Book of Nothing:");
        System.out.println(lib.findBookByTitle("The Big Book of Nothing").getISBN());

        System.out.println("Get title of the book with ISBN 5-11-532645-1");
        System.out.println(lib.findBookByISBN("5-11-532645-1").getTitle());

        System.out.println("\nPress ENTER to continue...");
        input.nextLine();

        System.out.println("Get books written by Ellsworth Eastlake");
        System.out.println(lib.findBooksByAuthor("Ellsworth Eastlake"));

        System.out.println("\nPress ENTER to continue...");
        input.nextLine();

        System.out.println("Get patron by the name Leif Eldrett at 221 Lullaby Lane");
        System.out.println(lib.findPatron("Leif Eldrett", "221 Lullaby Lane"));

        // Pick a book from the collection
        Book useBook = lib.findBookByTitle("This is the Fifth Book");
        Patron usePatron = lib.findPatron("Alyse Clover", "1 Packers Trail");
        Patron anotherPatron = lib.findPatron("Leland Sanper", "71630 Mcguire Circle");

        System.out.println(useBook);
        // Check out two copies of this book
        System.out.println("\t" + usePatron.getName() + " borrows 2 copies of " + useBook.getTitle());
        useBook.borrowCopies(usePatron, 2);
        // Check out a third copy of this book
        System.out.println("\t" + anotherPatron.getName() + " borrows 1 copy of " + useBook.getTitle());
        useBook.borrowCopy(anotherPatron);
        // Return one copy from first patron
        usePatron.findCopyOf(useBook).returnCopy();
        System.out.println("\t" + usePatron.getName() + " returns 1 copy of " + useBook.getTitle());
        System.out.println(useBook);

        System.out.println("\nPress ENTER to continue...");
        input.nextLine();

        System.out.println("Print the patron who has borrowed a book");
        System.out.println(usePatron);

        // Not strictly neccesary, as the program is about to exit - but it stops my IDE from complaining :)
        input.close();

        // System.out.println(lib.toString());
    }
}
