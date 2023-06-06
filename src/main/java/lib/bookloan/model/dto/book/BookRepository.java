package lib.bookloan.model.dto.book;

import lib.bookloan.model.dto.bookprop.BookGenre;
import lib.bookloan.model.dto.bookprop.BookVersion;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookRepository {
    private static final String TABLE_NORMAL_BOOK = "normalBook";
    private static final int INDEX_COL_NORMALBOOK_ID = 1;
    private static final int INDEX_COL_AUTHOR = 2;
    private static final int INDEX_COL_YEAR = 3;
    private static final int INDEX_COL_LANGUAGE = 4;
    private static final int INDEX_COL_NUMOFHARDCOPIES = 5;
    private static final int INDEX_COL_LOANPERIOD = 6;
    private static final int INDEX_COL_BOOKGENRE = 7;
    private static final int INDEX_COL_BOOKVERSION = 8;


    private static final String TABLE_ACOUSTIC_BOOK = "acousticBook";
    private static final int INDEX_COL_ACOUSTICBOOK_ID = 1;
    private static final int INDEX_COL_ACOUSTICBOOK_AUTHOR = 2;
    private static final int INDEX_COL_ACOUSTICBOOK_YEAR = 3;
    private static final int INDEX_COL_ACOUSTICBOOK_LANGUAGE = 4;
    private static final int INDEX_COL_FREE_TRIAL = 5;
    private static final int INDEX_COL_ACOUSTIC_BOOKGENRE = 6;
    private static final int INDEX_COL_ACCOUSTIC_BOOKVERSION = 7;



    private static final ResourceBundle rb = ResourceBundle.getBundle("data");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");
    private Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;


    public BookRepository() {
        boolean open = open();
    }
    public boolean open() {
        try {
            conn = DriverManager.getConnection(DRIVER_URL.concat(DATABASE_URL));
            return true;
        } catch (SQLException e) {
            System.out.println("Connection failed: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }




    public BookRepository(Connection conn) {
        this.conn = conn;
    }


    public ArrayList<NormalBook> canReadNormalBook() {
        try {
            String select = "SELECT * FROM ".concat(TABLE_NORMAL_BOOK);

            statement = conn.createStatement();
            statement.execute(select);

            resultSet = statement.getResultSet();
            ArrayList<NormalBook> nbookList = new ArrayList<>();
            while(resultSet.next()) {
                NormalBook nb = new NormalBook();
                nb.setId(resultSet.getInt(INDEX_COL_NORMALBOOK_ID));
                nb.setAuthor(resultSet.getString(INDEX_COL_AUTHOR));
                nb.setYear(resultSet.getInt(INDEX_COL_YEAR));
                nb.setLanguage(resultSet.getString(INDEX_COL_LANGUAGE));
                nb.setNumberOfHardCopies(resultSet.getInt(INDEX_COL_NUMOFHARDCOPIES));
                nb.setLoanPeriod(resultSet.getInt(INDEX_COL_LOANPERIOD));
                nb.setGenre(BookGenre.valueOf(resultSet.getString(INDEX_COL_BOOKGENRE)));
                nb.setVersion(BookVersion.valueOf(resultSet.getString(INDEX_COL_BOOKVERSION)));
                nbookList.add(nb);
            }
            return nbookList;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<AcousticBook> canReadAcousticBook() {
        try {
            String select = "SELECT * FROM ".concat(TABLE_ACOUSTIC_BOOK);

            statement = conn.createStatement();
            statement.execute(select);

            resultSet = statement.getResultSet();
            ArrayList<AcousticBook> abookList = new ArrayList<>();

            while(resultSet.next()) {
                AcousticBook ab = new AcousticBook();
                ab.setId(resultSet.getInt(INDEX_COL_ACOUSTICBOOK_ID));
                ab.setAuthor(resultSet.getString(INDEX_COL_ACOUSTICBOOK_AUTHOR));
                ab.setYear(resultSet.getInt(INDEX_COL_ACOUSTICBOOK_YEAR));
                ab.setLanguage(resultSet.getString(INDEX_COL_ACOUSTICBOOK_LANGUAGE));
                ab.setFreeTrialPeriod(resultSet.getInt(INDEX_COL_FREE_TRIAL));
                ab.setGenre(BookGenre.valueOf(resultSet.getString(INDEX_COL_ACOUSTIC_BOOKGENRE)));
                ab.setVersion(BookVersion.valueOf(resultSet.getString(INDEX_COL_ACCOUSTIC_BOOKVERSION)));
                abookList.add(ab);
            }
            return abookList;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    public NormalBook canSaveNormalBook(NormalBook normalBook) {
        String normalSql = ("INSERT INTO normalBook (author, year, language, numberOfHardCopies, loanPeriod, bookGenre, bookVersion) VALUES (?,?,?,?,?,?,?)");
        try {
            PreparedStatement pSmt = conn.prepareStatement(normalSql, Statement.RETURN_GENERATED_KEYS);
            pSmt.setString(1,normalBook.getAuthor().strip());
            pSmt.setInt(2,normalBook.getYear());
            pSmt.setString(3,normalBook.getLanguage().strip());
            pSmt.setInt(4,normalBook.getNumberOfHardCopies());
            pSmt.setInt(5,normalBook.getLoanPeriod());
            pSmt.setString(6,normalBook.getGenre().getValueOfGenre());
            pSmt.setString(7,normalBook.getVersion().getValueOfBookVersionAsString());

            int recordsAffected = pSmt.executeUpdate();
            ResultSet rSet = pSmt.getGeneratedKeys();

            while(rSet.next()) {
                int id = rSet.getInt(INDEX_COL_NORMALBOOK_ID); // resultset read column index at 1(1 indiced)
                normalBook.setId(id);                          // set bookId to autogenerated key/id from resultset
//
//                normalBook.setAuthor(rSet.getString("author"));
//                normalBook.setYear(rSet.getInt("year"));
//                normalBook.setLanguage(rSet.getString("language"));
//                normalBook.setNumberOfHardCopies(rSet.getInt("numberOfHardCopies"));
//                normalBook.setLoanPeriod(rSet.getInt("loanPeriod"));
//                normalBook.setGenre(BookGenre.valueOf(rSet.getString(INDEX_COL_BOOKGENRE)));
//                normalBook.setVersion(BookVersion.valueOf(rSet.getString(INDEX_COL_BOOKVERSION)));
            }
            System.out.printf("Records affected: %d%n", recordsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return normalBook;
    }

    public AcousticBook canSaveAcousticBook(AcousticBook acousticBook) {
        String acousticSql = ("INSERT INTO acousticBook (author, year, language, freeTrialPeriod, bookGenre, bookVersion) VALUES (?,?,?,?,?,?)");
        try {
            PreparedStatement pSmt = conn.prepareStatement(acousticSql, Statement.RETURN_GENERATED_KEYS);
            pSmt.setString(1,acousticBook.getAuthor().strip());
            pSmt.setInt(2,acousticBook.getYear());
            pSmt.setString(3,acousticBook.getLanguage().strip());
            pSmt.setInt(4,acousticBook.getFreeTrialPeriod());
            pSmt.setString(5,acousticBook.getGenre().getValueOfGenre());
            pSmt.setString(6,acousticBook.getVersion().getValueOfBookVersionAsString());

            int recordsAffected = pSmt.executeUpdate();
            ResultSet rSet = pSmt.getGeneratedKeys();

            while(rSet.next()) {
                int id = rSet.getInt(INDEX_COL_ACOUSTICBOOK_ID); // resultset read column index at 1(1 indiced)
                acousticBook.setId(id);                          // set bookId to autogenerated key/id from resultset
            }
            System.out.printf("Records affected: %d%n", recordsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acousticBook;
    }

}
