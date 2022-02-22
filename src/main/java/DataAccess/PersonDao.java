package DataAccess;

import model.Person;

import java.sql.*;
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
    public PersonDao(Connection iconn){
        conn = iconn;
    }

    /**
     * Interface with /person/[personID]
     * @param personID The Person's unique ID
     * @return Returns the single Person object with the specified ID (if the person is associated with the current user).
     */
    public Person getPersonByID(String personID) throws SQLException {
        String sql = "select * from Person where personID = \"" + personID + "\"";

        Person person;
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();

            if (res.isClosed()){
                throw new SQLException();
            }

            String username = res.getString(2);
            String firstName = res.getString(3);
            String lastName  = res.getString(4);
            char gender = res.getString(5).charAt(0);
            String fatherID = res.getString(6);
            String motherID = res.getString(7);
            String spouseID = res.getString(8);

            person = new Person(personID, username, firstName, lastName, gender, fatherID, motherID, spouseID);
        }

        return person;
    }

    /**
     * Interface with /person
     * @return Returns ALL family members of the current user.
     */
    public ArrayList<Person> getRelatives(String username) throws SQLException{
        String sql = "select * from Person where associatedUsername = \"" + username + "\"";

        ArrayList<Person> relatives = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet res = stmt.executeQuery();

            while (res.next()){
                String personID = res.getString(1);
                String associatedUsername = res.getString(2);
                String firstName = res.getString(3);
                String lastName = res.getString(4);
                char gender = res.getString(5).charAt(0);
                String fatherID = res.getString(6);
                String motherID = res.getString(7);
                String spouseID = res.getString(8);

                relatives.add(new Person(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID));
            }
        }

        return relatives;
    }

    public Person insertPerson(Person newPerson) throws SQLException {
        
        StringBuilder sqlbld = new StringBuilder("insert into Person Values (\"");
        sqlbld.append(newPerson.getPersonID());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getAssociatedUsername());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getFirstName());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getLastName());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getGender());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getFatherID());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getMotherID());
        sqlbld.append("\", \"");
        sqlbld.append(newPerson.getSpouseID());
        sqlbld.append("\")");

        String sql = sqlbld.toString();

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        }
        
        return newPerson;
    }

    public void clearPersonTable() throws SQLException {
        String sql = "Delete from Person";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        }
    }
}
