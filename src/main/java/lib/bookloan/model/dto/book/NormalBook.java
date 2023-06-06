package lib.bookloan.model.dto.book;

import lib.bookloan.model.dto.bookprop.BookGenre;
import lib.bookloan.model.dto.bookprop.BookVersion;
import lib.bookloan.model.dto.bookprop.Label;

public class NormalBook implements Book{
    private int id;
    private String author;
    private int year;
    private String language;
    private int numberOfHardCopies;
    private int loanPeriod;


    private BookGenre genre;
    private BookVersion version;
    private Label label;


    public NormalBook() {
    }

    public NormalBook(String author, int year, String language, int numberOfHardCopies, int loanPeriod) {
        this.author = author;
        this.year = year;
        this.language = language;
        this.numberOfHardCopies = numberOfHardCopies;
        this.loanPeriod = loanPeriod;
    }

    public NormalBook(String author, int year, String language, int numberOfHardCopies, int loanPeriod, BookGenre genre, BookVersion version) {
        this.author = author;
        this.year = year;
        this.language = language;
        this.numberOfHardCopies = numberOfHardCopies;
        this.loanPeriod = loanPeriod;
        this.genre = genre;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public NormalBook(String author) {
        this.author = author;
    }

    public NormalBook(String author, int year, String language) {
        this.author = author;
        this.year = year;
        this.language = language;
    }

    public NormalBook(int id, String author, int year, String language, int numberOfHardCopies, int loanPeriod, String genre, BookVersion version) {
        this.id = id;
        this.author = author;
        this.year = year;
        this.language = language;
        this.numberOfHardCopies = numberOfHardCopies;
        this.loanPeriod = loanPeriod;
        this.genre = BookGenre.valueOf(genre);
        this.version = version;
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

    public int getNumberOfHardCopies() {
        return numberOfHardCopies;
    }

    public void setNumberOfHardCopies(int numberOfHardCopies) {
        this.numberOfHardCopies = numberOfHardCopies;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
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

    public String getLabel() {
        return label.getLabel();
    }

    public void setLabel(String label) {
        this.label.setLabel(label);
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
        return String.format("Id: %d Author: %s Year: %d Language: %s HardCopy %d: Loan: %d Genre: %s Version: %s%n",
                getId(),getAuthor(),getYear(),
                getLanguage(),getNumberOfHardCopies(),getLoanPeriod(),genre.getGenre(),version.getVersion()
                );
        //Genre: %s Version: %s getGenre().getValueOfGenre(),getVersion().getValueOfBookVersionAsString()
    }
}
