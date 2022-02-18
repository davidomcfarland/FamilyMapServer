package DataAccessTest;

import DataAccess.PersonDao;
import model.Person;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static global.ProjectData.dbPath;

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
            PersonDao personDao = new PersonDao(conn);

            Person actual = personDao.getPersonByID("person001_dave_fakedata");

            Person expected = new Person("person001_dave_fakedata",   "alpha",   "alphafirstName",   "alphaLastName", 'm', "father001_dave_fakedata", "mother001_dave_fakedata", "spouse001_dave_fakedata");

            Assertions.assertEquals(expected, actual);
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
            PersonDao personDao = new PersonDao(conn);

            Assertions.assertThrows(SQLException.class, () -> personDao.getPersonByID("not a valid person ID"), "Invalid response on negative retrieval for not-found data:\n");
        }
        catch (SQLException ex) {
            Assertions.fail("unable to connect to database:\n" + ex.getMessage());
        }
    }

    @DisplayName("Insertion method positive test case")
    @Test
    void positiveInsertion(){}

    @DisplayName("Insertion method negative test case")
    @Test
    void negativeInsertion(){}

    @DisplayName("Clear Test")
    @Test
    void clearTest(){}
}
