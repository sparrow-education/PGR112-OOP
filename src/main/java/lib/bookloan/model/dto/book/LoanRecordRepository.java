package lib.bookloan.model.dto.book;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoanRecordRepository {
    private static final ResourceBundle rb = ResourceBundle.getBundle("data");
    private static final String DRIVER_URL = rb.getString("URL");
    private static final String DATABASE_URL = rb.getString("DATABASE");
    private Connection conn;
    private Statement statement;
    private PreparedStatement prepStatement;
    private ResultSet resultSet;
    private ArrayList<Book> rentedBookList = new ArrayList<>();



    private static final int INDEX_COL_LOANRECORD_ID = 1;
    private static final int INDEX_COL_USER_ID = 1;
    private static final int INDEX_COL_USERNAME = 2;
    private static final int INDEX_COL_BOOK_ID = 3;
    private static final int INDEX_COL_BOOKGENRE = 4;
    private static final int INDEX_COL_BOOKVALUE = 5;


    public LoanRecordRepository() {
        open();
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

    public LoanRecordRepository(Connection conn) {
        this.conn = conn;
    }

    public int canRent(ArrayList<Integer> bookId) {
        String rentSql = """
                UPDATE normalBook set loanPeriod = 0 where id = ?;   
                """;
        try(PreparedStatement pStmt = conn.prepareStatement(rentSql)) {
            for (Integer id : bookId) {
                pStmt.setInt(1, id);
                int recordsAffected = pStmt.executeUpdate();
                if (recordsAffected > 0) {
                    System.out.println("Successfully rented: " + recordsAffected);
                    return recordsAffected;
                } else {
                    System.out.println("Query failed row affected: " + recordsAffected);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int canReturn(ArrayList<Integer> bookId) {
        String returnSql = """
                UPDATE normalBook set loanPeriod = 1 where id = ?;   
                """;
        try(PreparedStatement pStmt = conn.prepareStatement(returnSql)) {
            for (Integer id : bookId) {
                pStmt.setInt(1, id);
                int recordsAffected = pStmt.executeUpdate();
                if (recordsAffected > 0) {
                    System.out.println("Successfully returned: " + recordsAffected);
                    return recordsAffected;
                } else {
                    System.out.println("Query failed row affected: " + recordsAffected);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int canRentAcoBook(ArrayList<Integer> trials, ArrayList<Integer> bookId) {
        String deleteSql = """
                UPDATE acousticBook set freeTrialPeriod = ? WHERE id = ?
                """;
        try(PreparedStatement pStmt = conn.prepareStatement(deleteSql)) {
            for (Integer trial : trials) {
                pStmt.setInt(1, trial);
            }
            for (Integer id : bookId) {
                pStmt.setInt(2, id);
            }
            int records = pStmt.executeUpdate();
            if (records > 0) {
                System.out.println("Successfully rented: " + records);
                return records;
            } else System.out.println("Query failed row affected: " + records);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int canRentNormBook() {
        String deleteSql = """
                DELETE FROM normalBook WHERE id = ?
                """;
        try(PreparedStatement pStmt = conn.prepareStatement(deleteSql)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter book id: ");
            String input = scanner.nextLine();
            pStmt.setString(1, input);
            int records = pStmt.executeUpdate();
            Book book = new NormalBook(input);
            rentedBookList.add(book);
            System.out.println(rentedBookList);

            if (records > 0) {
                System.out.println("Successfully rented: " + records);
                return records;
            } else System.out.println("Query failed row affected: " + records);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public void canSave(LoanRecord loanRecord) {
        String loanRecAutoGenSql = "INSERT INTO loanRecord (userName, bookId, bookGenre, bookVersion) VALUES (?,?,?,?)";
        //String loanRecManualSql = "INSERT INTO loanRecord (userId, userName, bookId, bookGenre, bookVersion) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pStmt = conn.prepareStatement(loanRecAutoGenSql, Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1,loanRecord.getUserName());
            pStmt.setInt(2,loanRecord.getBookId());
            pStmt.setString(3,loanRecord.getGenre().getValueOfGenre());
            pStmt.setString(4,loanRecord.getVersion().getValueOfBookVersionAsString());

            // For test with manual id insert
            /*pStmt.setInt(1,loanRecord.getUserId());
            pStmt.setString(2,loanRecord.getUserName());
            pStmt.setInt(3,loanRecord.getBookId());
            pStmt.setString(4,loanRecord.getGenre().getValueOfGenre());
            pStmt.setString(5,loanRecord.getVersion().getValueOfBookVersionAsString());*/

            int recAffected = pStmt.executeUpdate();
            ResultSet rSet = pStmt.getGeneratedKeys();

            while(rSet.next()) {
                int recId = rSet.getInt(INDEX_COL_LOANRECORD_ID);
                loanRecord.setUserId(recId);
            }
            System.out.printf("Records affected: %d%n", recAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
