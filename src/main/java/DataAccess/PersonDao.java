package DataAccess;

import model.Person;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Person Data Access Object
 */
public class PersonDao {
    /**
     * SQL Database connection
     */
    Connection conn;

    /**
     * Create a new Person DAO using the connection iconn
     * @param iconn existing SQL Database connection
     */
    PersonDao(Connection iconn){
        conn = iconn;
    }

    /**
     * Interface with /person/[personID]
     * @param personID The Person's unique ID
     * @return Returns the single Person object with the specified ID (if the person is associated with the current user).
     */
    public Person getPersonByID(String personID){return null;}

    /**
     * Interface with /person
     * @return Returns ALL family members of the current user.
     */
    public ArrayList<Person> getRelatives(){return null;}

    public Person insertPerson(Person newPerson){return null;}
}
