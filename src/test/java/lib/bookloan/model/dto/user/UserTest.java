package lib.bookloan.model.dto.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
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
        UserRepositoryMenu repo = new UserRepositoryMenu(conn);
        System.out.println(repo);
    }

    @Test
    void showUsrMenu() {
        UserRepositoryMenu repo = new UserRepositoryMenu(conn);
        User newUser1 = new User("The One");
        User newUser2 = new User("Neo");
        User saveUser1 = repo.canInsert(newUser1);
        User saveUser2 = repo.canInsert(newUser2);
        assertThat(saveUser1.getId()).isNotEqualTo(saveUser2.getId());
    }


}