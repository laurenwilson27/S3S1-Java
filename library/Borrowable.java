package library;

public interface Borrowable {
    public void borrowCopy(Patron borrower);
    public void returnCopy();
}
