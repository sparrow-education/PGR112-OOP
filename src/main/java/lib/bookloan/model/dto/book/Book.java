package lib.bookloan.model.dto.book;

public interface Book {
    /**
     * All 'Book' has or has not
     * @return
     */
     int canLoan();
     int canReturn();
     boolean hasSubscription();
     boolean isTrial();

}
