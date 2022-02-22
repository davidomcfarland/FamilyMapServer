package DataAccessTest;

import DataAccess.NotFoundException;
import DataAccess.UserDao;
import model.Authtoken;
import model.User;
import org.junit.jupiter.api.*;
import java.sql.*;

import static global.ProjectData.dbPath;

public class UserDaoTest {
    @BeforeAll
    static void allPrep(){
        FakeEntries.fill();
    }

    @AfterAll
    public static void allCleanup(){
        FakeEntries.remove();
    }

    @DisplayName("Retrieval method positive test case")
    @Test
    void positiveRetrieval(){
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;

            UserDao userDao = new UserDao(conn);

            User actual = userDao.getUser("alpha_dave_fakedata");

            User expected = new User("alpha_dave_fakedata", "alphapassword", "alpha@domain.com", "alphafirstName", "alphaLastName", 'm');

            Assertions.assertEquals(expected, actual, "Objects are not the same:");
        }
        catch (SQLException ex){
            Assertions.fail(ex.getMessage());
        }
    }

    @DisplayName("Retrieval method negative test case")
    @Test
    void negativeRetrieval() throws SQLException {
        Connection conn = DriverManager.getConnection(dbPath);
        UserDao userDao = new UserDao(conn);

        Assertions.assertThrows(SQLException.class, () -> userDao.getUser("not a real username"));
    }

    @DisplayName("Insertion method positive test case")
    @Test
    void positiveInsertion(){
        User inputUser = new User("davidomcfarland", "123456789", "davidomcfarland@gmail.com", "David", "McFarland", 'm');
        User newUser = null;
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)) {
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

        try (Connection c = DriverManager.getConnection(dbPath)) {
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

        try (Connection c = DriverManager.getConnection(dbPath)){
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

        FakeEntries.fill();
    }

    @DisplayName("Login User Positives")
    @Test
    void loginPositive(){

        Connection conn = null;

        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;

            conn.setAutoCommit(false);

            UserDao userDao = new UserDao(conn);

            Authtoken auth1 = userDao.loginUser("alpha_dave_fakedata", "alphapassword");
            Authtoken auth2 = userDao.loginUser("beta_dave_fakedata", "betapassword");
            Authtoken auth3 = userDao.loginUser("gamma_dave_fakedata", "gammapassword");

            Assertions.assertNotNull(auth1);
            Assertions.assertNotNull(auth2);
            Assertions.assertNotNull(auth3);

            Assertions.assertNotEquals(auth1, auth2);
            Assertions.assertNotEquals(auth1, auth3);
            Assertions.assertNotEquals(auth2, auth3);

            conn.rollback();
        }
        catch (Exception ex) {
            try {
                conn.rollback();
            }
            catch (Exception ex2){
                // do nothing, there wasn't a connection to roll back
            }
            Assertions.fail(ex.getMessage());
        }
    }

    @DisplayName("Login User Negatives")
    @Test
    void wrongLogin(){
        Connection conn = null;

        try (Connection c = DriverManager.getConnection(dbPath)) {
            conn = c;

            conn.setAutoCommit(false);

            UserDao userDao = new UserDao(conn);

            Assertions.assertThrows(NotFoundException.class, () -> userDao.loginUser("WRONGUSERNMAE", "alphapassword"));
            Assertions.assertThrows(NotFoundException.class, () -> userDao.loginUser("alpha_dave_fakedata", "WRONGPASSWORD"));
            Assertions.assertThrows(NotFoundException.class, () -> userDao.loginUser("WRONGUSERNAME", "WRONGPASSWORD"));

            conn.rollback();
        }
        catch (Exception ex){
            try {
                conn.rollback();
            }
            catch (Exception ex2){
                // do nothing, conn is null
            }

            Assertions.fail(ex.getMessage());
        }
    }

    @DisplayName("Fill User Positive")
    @Test
    void fillPositive(){Assertions.fail();}

    @DisplayName("Fill user Negative")
    @Test
    void fillNegative(){Assertions.fail();}



}
