package lib.bookloan.controller;

import lib.bookloan.model.dto.user.User;
import lib.bookloan.model.dto.user.UserRepositoryMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
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



}