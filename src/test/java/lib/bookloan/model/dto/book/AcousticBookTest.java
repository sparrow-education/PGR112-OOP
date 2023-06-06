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
import static org.junit.jupiter.api.Assertions.*;

class AcousticBookTest {
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
        BookRepository bookRepo = new BookRepository(conn);
        System.out.println(bookRepo);
    }

    @Test
    void isAcousticBook() {
        assertEquals(1, BookVersion.ACOUSTIC.getValueOfBookVersionAsInt());
    }

    @Test
    void isNotEqualsToAcousticBook() {
        assertThat("hardcopy").isNotEqualToIgnoringCase(BookVersion.ACOUSTIC.getValueOfBookVersionAsString());

    }

    @Test
    void canSaveAcoustic() {
        BookRepository repo = new BookRepository(conn);
        AcousticBook lotr = new AcousticBook("J.R.R Tolkien",1954, "English", 1,BookGenre.SCIFI, BookVersion.ACOUSTIC);
        AcousticBook saveLotr = repo.canSaveAcousticBook(lotr);


        assertThat(saveLotr.getAuthor()).isEqualTo("J.R.R Tolkien");
    }


}