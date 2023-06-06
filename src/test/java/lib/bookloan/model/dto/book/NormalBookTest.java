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

class NormalBookTest {
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
    void canSaveNormal() {
        BookRepository repo = new BookRepository(conn);
        NormalBook manga = new NormalBook("Gyo",1998,"Japanese",1,1,BookGenre.MANGA,BookVersion.HARDCOPY);
        NormalBook saveManga = repo.canSaveNormalBook(manga);

        assertThat(saveManga.getAuthor()).isEqualToIgnoringCase("Gyo");

    }

}