package DataAccessTest;

import DataAccess.PersonDao;
import DataAccess.UserDao;
import model.Person;
import org.junit.jupiter.api.*;

import java.sql.*;

import static global.ProjectData.dbPath;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {

    @BeforeAll
    static void allPrep(){
        FakeEntries.fill();
    }

    @BeforeEach
    void testPrep(){}

    @AfterEach
    void testCleanup(){}

    @AfterAll
    static void allCleanup(){
        FakeEntries.remove();
    }

    @DisplayName("Retrieval method positive test case")
    @Test
    void positiveRetrieval(){
        Connection conn = null;

        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;
            PersonDao personDao = new PersonDao(conn);

            Person actual = personDao.getPersonByID("person001_dave_fakedata");

            Person expected = new Person("person001_dave_fakedata",   "alpha",   "alphafirstName",   "alphaLastName", 'm', "father001_dave_fakedata", "mother001_dave_fakedata", "spouse001_dave_fakedata");

            assertEquals(expected, actual);
        }
        catch (SQLException ex){
            Assertions.fail("positive retrieval fail:\n" + ex.getMessage());
        }
    }

    @DisplayName("Retrieval method negative test case")
    @Test
    void negativeRetrieval(){
        Connection conn = null;

        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;
            PersonDao personDao = new PersonDao(conn);

            assertThrows(SQLException.class, () -> personDao.getPersonByID("not a valid person ID"), "Invalid response on negative retrieval for not-found data:\n");
        }
        catch (SQLException ex) {
            fail("unable to connect to database:\n" + ex.getMessage());
        }
    }

    @DisplayName("Insertion method positive test case")
    @Test
    void positiveInsertion(){
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;

            conn.setAutoCommit(false);

            PersonDao personDao = new PersonDao(conn);

            Person newPerson = new Person("positiveInsertionPerson", "username", "firstName", "lastName", 'f', "6", "9", "123");

            personDao.insertPerson(newPerson);

            Person addedPerson = personDao.getPersonByID("positiveInsertionPerson");

            assertEquals(newPerson, addedPerson);

            conn.rollback();
        }
        catch (Exception ex) {
            try {
                conn.rollback();
            }
            catch (Exception ex2) {
                // there is no connection. No error
            }
            fail(ex.getMessage());
        }
    }

    @DisplayName("Insertion method negative test case")
    @Test
    void negativeInsertion(){
        Connection conn = null;
        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;
            conn.setAutoCommit(false);

            PersonDao personDao = new PersonDao(conn);

            Person newPerson = new Person("negativeInsertionPerson", "username", "firstName", "lastName", 'f', "6", "9", "123");

            personDao.insertPerson(newPerson);
            assertThrows(SQLException.class, () -> personDao.insertPerson(newPerson), "Person inserted Twice:\n");

            conn.rollback();
        }
        catch (Exception ex) {
            try {
                conn.rollback();
            }
            catch (Exception ex2){
                // conn was null, no need for rollback or further action
            }
            fail(ex.getMessage());
        }
    }

    @DisplayName("Clear Test")
    @Test
    void clearTest(){
        Connection conn = null;

        try (Connection c = DriverManager.getConnection(dbPath)){
            conn = c;
            conn.setAutoCommit(false);
            PersonDao personDao = new PersonDao(conn);

            personDao.clearPersonTable();

            int actualSize = -1;
            try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM Person;")){
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
