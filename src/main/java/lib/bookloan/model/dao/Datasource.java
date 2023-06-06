package lib.bookloan.model.dao;

import java.sql.*;
import java.util.ResourceBundle;

public class Datasource {
    private static final ResourceBundle rb = ResourceBundle.getBundle("data");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");

    protected Connection conn = null;
    protected Statement statement;
    protected PreparedStatement prepStatement;
    protected ResultSet resultSet;


    protected Datasource() {
        boolean open = open();
        System.out.println("Connection success: "+open);
    }

    private boolean open() {
        try {
            conn = DriverManager.getConnection(DRIVER_URL.concat(DATABASE_URL));
            return true;
        } catch (SQLException e) {
            System.out.println("Connection failed: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    protected void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Close failed: "+e.getMessage());
        }
    }
}
