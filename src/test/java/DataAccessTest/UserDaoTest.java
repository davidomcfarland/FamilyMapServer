package DataAccessTest;

import DataAccess.UserDao;
import model.User;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class UserDaoTest {
    @BeforeAll
    static void allPrep(){
        Connection conn = null;
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")){
            conn = c;

            try (Scanner scanner = new Scanner(new File("sql_files" + File.separator + "test" + File.separator + "FillTables.sql"))) {
                while (scanner.hasNext()) {
                    try (PreparedStatement stmt = conn.prepareStatement(scanner.useDelimiter("\r\n").next())){
                        stmt.executeUpdate();
                    }
                }
            }


        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            Assertions.fail();
        }
    }

    @BeforeEach
    void testPrep() {
    }

    @AfterAll
    public static void testCleanup(){
        //Connection conn = null;
        //try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")){
        //    conn = c;
        //    String sql;
        //    try (Scanner scanner = new Scanner(new File("sql_files" + File.separator + "test" + File.separator + "ClearTables.sql"))) {
        //       sql = scanner.useDelimiter("\\A").next();
        //    }
        //
        //    try (PreparedStatement stmt = conn.prepareStatement(sql)){
        //        stmt.executeUpdate();
        //    }
        //}
        //catch (Exception ex){
        //    Assertions.fail(ex.getMessage());
        //}
    }

    @DisplayName("Retrieval method positive test case")
    @Test
    void positiveRetrieval(){
        Connection conn = null;
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")){
            conn = c;

            UserDao userDao = new UserDao(conn);

            User actual = userDao.getUser("alpha");

            User expected = new User("alpha", "alphapassword", "alpha@domain.com", "alphafirstName", "alphaLastName", 'm');

            Assertions.assertEquals(expected, actual, "Objects are not the same:");
        }
        catch (SQLException ex){
            Assertions.fail(ex.getMessage());
        }
    }

    @DisplayName("Retrieval method negative test case")
    @Test
    void negativeRetrieval() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:db/database.db");
        UserDao userDao = new UserDao(conn);

        Assertions.assertThrows(SQLException.class, () -> userDao.getUser("not a real username"));
    }

    @DisplayName("Insertion method positive test case")
    @Test
    void positiveInsertion(){
        User inputUser = new User("davidomcfarland", "123456789", "davidomcfarland@gmail.com", "David", "McFarland", 'm');
        User newUser = null;
        Connection conn = null;
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")) {
            conn = c;
            UserDao userDao = new UserDao(conn);

            conn.setAutoCommit(false);

            userDao.registerUser(inputUser);

            User actual = userDao.getUser("davidomcfarland");
            User expected = new User("davidomcfarland", "123456789", "davidomcfarland@gmail.com", "David", "McFarland", 'm');

            conn.rollback();

            Assertions.assertEquals(expected, actual, "Insertion failed");
        }
        catch (SQLException ex){
            Assertions.fail();
        }
    }

    @DisplayName("Insertion method negative test case")
    @Test
    void negativeInsertion(){
        User inputUser = new User("davidomcfarland", "123456789", "davidomcfarland@gmail.com", "David", "McFarland", 'm');
        User newUser = null;
        Connection conn = null;

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")) {
            conn = c;
            UserDao userDao = new UserDao(conn);

            conn.setAutoCommit(false);

            userDao.registerUser(inputUser);

            Assertions.assertThrows(SQLException.class, () -> userDao.registerUser(inputUser) );
        }
        catch (SQLException ex){
            Assertions.fail(ex.getMessage());
        }
    }

    @DisplayName("Clear Test")
    @Test
    void clearTest(){
        Connection conn = null;

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:db/database.db")){
            conn = c;
            conn.setAutoCommit(false);
            UserDao userDao = new UserDao(conn);

            userDao.clearUserTable();

            int actualSize = -1;
            try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM User;")){
                ResultSet res = stmt.executeQuery();

                actualSize = res.getInt(1);
            }

            Assertions.assertEquals(0, actualSize, "Rows not deleted");

            conn.rollback();
        }
        catch (SQLException ex){
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Assertions.fail(ex.getMessage());
        }
    }
}
