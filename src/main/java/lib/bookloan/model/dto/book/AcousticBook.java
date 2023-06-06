package lib.bookloan.model.dto.book;

import lib.bookloan.model.dto.bookprop.BookGenre;
import lib.bookloan.model.dto.bookprop.BookVersion;
import lib.bookloan.model.dto.bookprop.Subscription;

public class AcousticBook implements Book {
    private int id;
    private String author;
    private int year;
    private String language;
    private int freeTrialPeriod;


    private BookGenre genre;
    private BookVersion version; // Enum
    private Subscription subs;   // Enum



    public AcousticBook(String author) {
        this.author = author;
    }

    public AcousticBook(String author, int year, String language, int freeTrialPeriod, BookGenre genre, BookVersion version) {
        this.author = author;
        this.year = year;
        this.language = language;
        this.freeTrialPeriod = freeTrialPeriod;
        this.genre = genre;
        this.version = version;
    }

    public AcousticBook(int id, String author, int year, String language, int freeTrialPeriod, BookGenre genre, BookVersion version) {
        this.id = id;
        this.author = author;
        this.year = year;
        this.language = language;
        this.freeTrialPeriod = freeTrialPeriod;
        this.genre = genre;
        this.version = version;
    }

    public AcousticBook() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFreeTrialPeriod() {
        return freeTrialPeriod;
    }

    public void setFreeTrialPeriod(int freeTrialPeriod) {
        this.freeTrialPeriod = freeTrialPeriod;
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

    public Subscription getSubs() {
        return subs;
    }

    public void setSubs(Subscription subs) {
        this.subs = subs;
    }

    @Override
    public int canLoan() {
        return 0;
    }

    @Override
    public int canReturn() {
        return 0;
    }

    @Override
    public boolean hasSubscription() {
        return false;
    }

    @Override
    public boolean isTrial() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Id: %d Author: %s Year: %d Language: %s Trial: %d Genre: %s Version: %s%n",
                getId(),getAuthor(),getYear(),getLanguage(),getFreeTrialPeriod(),genre.getGenre(),version.getVersion());
    }
}
