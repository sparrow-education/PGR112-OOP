package lib.bookloan.model.dto.book;

import lib.bookloan.model.dto.bookprop.BookGenre;
import lib.bookloan.model.dto.bookprop.BookVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

class LoanRecordTest {
    private static final ResourceBundle rb = ResourceBundle.getBundle("test");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");
    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        conn = DriverManager.getConnection(DRIVER_URL.concat(DATABASE_URL));
    }

    @Test
    void canGetConnection() {
        LoanRecordRepository loanRepo = new LoanRecordRepository(conn);
        System.out.println(loanRepo);
    }


    @Test
    void canSaveLoan() {
        LoanRecordRepository loanRepo = new LoanRecordRepository(conn);
        LoanRecord rec1 = new LoanRecord("Hito",2, BookGenre.CRIME, BookVersion.ACOUSTIC);
        loanRepo.canSave(rec1);

        assertThat(rec1.getBookId()).isGreaterThan(0);

        // test with manual id insert
        /*LoanRecord rec1 = new LoanRecord(2,"Dummy",7, BookGenre.MANGA, BookVersion.HARDCOPY);
        loanRepo.canSave(rec1);

        assertThat(rec1.getBookId()).isGreaterThan(0);*/
    }
}