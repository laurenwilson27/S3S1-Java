package library;

/**
 * A simple enumeration of all possible statuses of a book. Note that due dates are not implemented.
 */
public enum Status {
    /**
     * A book which is available for borrowing.
     */
    AVAILABLE,
    /**
     * A book which is currently checked out by a patron.
     */
    CHECKED_OUT,
    /**
     * A book which is overdue. Due dates are not implemented, so this is unused.
     */
    OVERDUE;
}
