package lib.bookloan.model.dto.book;

import lib.bookloan.model.dto.bookprop.BookGenre;
import lib.bookloan.model.dto.bookprop.BookVersion;

public class LoanRecord {
    private int userId;
    private String userName;
    private int bookId;
    private BookGenre genre;
    private BookVersion version;


    public LoanRecord(String userName, int bookId, BookGenre genre, BookVersion version) {
        this.userName = userName;
        this.bookId = bookId;
        this.genre = genre;
        this.version = version;
    }

    public LoanRecord(int userId, String userName, int bookId, BookGenre genre, BookVersion version) {
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.genre = genre;
        this.version = version;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public BookVersion getVersion() {
        return version;
    }

    public void setVersion(BookVersion version) {
        this.version = version;
    }
}
