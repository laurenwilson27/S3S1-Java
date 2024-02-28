package library;

/**
 * An interface for things which can be borrowed by a Patron and returned.
 */
public interface Borrowable {
    /**
     * Marks this item as being borrowed by a Patron.
     * 
     * @param borrower The borrowing Patron.
     */
    public void borrowCopy(Patron borrower);
    /**
     * Returns this item.
     */
    public void returnCopy();
}
