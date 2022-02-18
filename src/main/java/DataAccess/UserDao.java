package DataAccess;

import model.User;
import model.Authtoken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * User Data Access Object
 */
public class UserDao {
    /**
     * SQL Database Connection
     */
    Connection conn;

    /**
     * Creates a new UserDao from iconn
     * @param iconn an existing SQL Database Connection
     */
    public UserDao(Connection iconn){
        conn = iconn;
    }

    /**
     * Interface with /user/register
     * Creates a new user account (user row in the database)
     * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
     * Logs the user in
     * @param user User object with new user's information
     * @return Returns an authtoken for a new session
     */
    public Authtoken registerUser(User user) throws SQLException {

        // Bob the (String) builder
        StringBuilder bob = new StringBuilder("insert into User Values (\"");
        bob.append(user.getUsername());
        bob.append("\", \"");
        bob.append(user.getPassword());
        bob.append("\", \"");
        bob.append(user.getEmail());
        bob.append("\", \"");
        bob.append(user.getFirstName());
        bob.append("\", \"");
        bob.append(user.getLastName());
        bob.append("\", \"");
        bob.append(user.getGender());
        bob.append("\", \"");
        bob.append(UUID.randomUUID().toString().replaceAll("-", ""));
        bob.append("\");");

        String sql = bob.toString();

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        }

        return new Authtoken(UUID.randomUUID().toString().replaceAll("-", ""), user.getUsername());
    }

    /**
     * Interface with /user/login
     * Logs the user in
     * @param username Username of an existing user
     * @param password The user's password
     * @return Returns an authtoken for a new session
     */
    public Authtoken loginUser(String username, String password){return null;}

    /**
     * Interface with /fill/[username]/{generations}
     * Populates the server's database with generated data for the specified username. If there is any data in the database already associated with the given username, it is deleted.
     * @param username The required "username" parameter must be a user already registered with the server.
     * @param generations The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
     */
    public void fillUser(String username, int generations){}
    /**
     * Interface with /fill/[username]/{generations}
     * Populates the server's database with generated data for the specified username. If there is any data in the database already associated with the given username, it is deleted.
     * @param username The required "username" parameter must be a user already registered with the server.
     */
    public void fillUser(String username){
        fillUser(username, 4);
    }

    public User getUser(String username) throws SQLException {
        String sql = "select * from User where username = \"" + username + "\"";

        String password, email, firstName, LastName, personID;
        char gender;

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet result = stmt.executeQuery();

            password  = result.getString(2);
            email     = result.getString(3);
            firstName = result.getString(4);
            LastName  = result.getString(5);
            gender    = result.getString(6).charAt(0);
            personID  = result.getString(7);
        }

        return new User(username, password, email, firstName, LastName, gender);
    }

    public void clearUserTable() throws SQLException {
        String sql = "delete from User";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        }
    }
}
