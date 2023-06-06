package lib.bookloan.model.dto.user;

import lib.bookloan.model.dao.Datasource;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


public class UserRepositoryMenu extends Datasource {
    private static ArrayList<User> userRepoList = new ArrayList<>();

    private static final String TABLE_USER = "user";
    private static final int INDEX_COL_USER_ID = 1;
    private static final int INDEX_COL_USER_NAME = 2;


    private static final ResourceBundle rb = ResourceBundle.getBundle("data");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");
    private Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;


    public UserRepositoryMenu() {
        boolean open = open();
    }
    public UserRepositoryMenu(Connection connection) {
        this.conn = connection;
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

    public ArrayList<User> canRead() {
        try {
            String select = "SELECT * FROM ".concat(TABLE_USER);

            statement = conn.createStatement();
            statement.execute(select);

            resultSet = statement.getResultSet();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(INDEX_COL_USER_ID));
                user.setName(resultSet.getString(INDEX_COL_USER_NAME));
                userRepoList.add(user);
            }
            return userRepoList;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public User canInsert(User user) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter user name: ");
        String input = scan.nextLine();
        String sql = ("INSERT INTO user (userName) VALUES (?)");
        try {
            PreparedStatement pSmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pSmt.setString(1,input.substring(0, 1).toUpperCase().concat(input.substring(1)).strip());
            int recordsAffected = pSmt.executeUpdate();
            ResultSet rSet = pSmt.getGeneratedKeys();

            while(rSet.next()) {
                int id = rSet.getInt(INDEX_COL_USER_ID);
                user.setId(id);
            }
            System.out.printf("Records affected: %d%n", recordsAffected);
            return user;
        } catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
        }
        return null;
    }







    protected void deleteOps() {
    }
    public void canCreateDatabase() {
        try {
            String userSql = """
                    CREATE TABLE IF NOT EXISTS user (
                        userId INTEGER PRIMARY KEY UNIQUE,
                        userName TEXT NOT NULL,
                        UNIQUE (userName)
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
                        bookGenre TEXT CHECK ( bookGenre IN ('ACTION','CRIME','SCIFI','DRAMA','MANGA') ) DEFAULT 'OTHER',
                        bookVersion TEXT CHECK ( bookVersion IN ('HARDCOPY','ACOUSTIC') )
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        UserRepositoryMenu userRepositoryMenu = new UserRepositoryMenu();
        userRepositoryMenu.canInsert(new User("Rio"));
        ArrayList<User> users = userRepositoryMenu.canRead();
        users.forEach(System.out::print);
    }
}
