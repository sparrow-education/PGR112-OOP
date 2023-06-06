package lib.bookloan.model.dto.user;

import lib.bookloan.model.dto.book.AcousticBook;
import lib.bookloan.model.dto.book.Book;
import lib.bookloan.model.dto.book.NormalBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryMenuTest {
    private static final ResourceBundle rb = ResourceBundle.getBundle("test");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");
    private Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(DRIVER_URL.concat(DATABASE_URL));
    }


    @Test
    void canGetConnection() {
        UserRepositoryMenu repo = new UserRepositoryMenu(conn);
        System.out.println(repo);
    }


    @Test
    void canSaveUsers() {
        UserRepositoryMenu repo = new UserRepositoryMenu(conn);
        User newUser1 = new User(1,"dummy1");
        User newUser2 = new User(2,"dummy2");
        User saveUser1 = repo.canInsert(newUser1);
        User saveUser2 = repo.canInsert(newUser2);
        assertThat(saveUser1.getId()).isNotEqualTo(saveUser2.getId());

    }

    @Test
    void canShowBook() {

        Map<Integer, Book> acousticBookMap = new HashMap<>();
        acousticBookMap.put(1,new AcousticBook("acoustic book"));
        acousticBookMap.put(2,new NormalBook("normal book"));

        for(Map.Entry<Integer,Book> bookEntry : acousticBookMap.entrySet()) {
            System.out.printf("id: %s book: %s%n",bookEntry.getKey().toString(),bookEntry.getValue().toString());
        }
    }

    @Test
    void canCreateDatabase() {
        try {
            String userSql = """
                    CREATE TABLE IF NOT EXISTS user (
                        userId INTEGER PRIMARY KEY,
                        userName TEXT NOT NULL
                    )
                    """;
            String nBSql = """
                    CREATE TABLE IF NOT EXISTS normalBook (
                        id INTEGER PRIMARY KEY,
                        author TEXT NOT NULL,
                        year INTEGER,
                        language TEXT,
                        numberOfHardCopies INTEGER,
                        loanPeriod INTEGER,
                        bookGenre TEXT,
                        bookVersion TEXT
                    )
                    """;
            String aBSql = """
                    CREATE TABLE IF NOT EXISTS acousticBook (
                        id INTEGER PRIMARY KEY,
                        author TEXT NOT NULL,
                        year INTEGER,
                        language TEXT,
                        freeTrialPeriod INTEGER,
                        bookGenre TEXT,
                        bookVersion TEXT
                    )
                    """;
            String recSql = """
                    PRAGMA foreign_keys = ON;
                    CREATE TABLE IF NOT EXISTS loanRecord (
                        userId INTEGER PRIMARY KEY,
                        userName TEXT NOT NULL,
                        bookId INTEGER NOT NULL,
                        bookGenre TEXT,
                        bookVersion TEXT,
                        FOREIGN KEY (bookId)
                            REFERENCES user(userId)
                    )
                    """;
            statement = conn.createStatement();
            statement.addBatch(userSql);
            statement.addBatch(nBSql);
            statement.addBatch(aBSql);
            statement.addBatch(recSql);
            int[] tablesInserted = statement.executeBatch();
            boolean isTrue = tablesInserted.length > 0;
            String msg = String.valueOf(isTrue).equals("true") ? "Table created" : "Table exists";
            System.out.println(msg);

            assertThat(msg).isEqualToIgnoringCase("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}