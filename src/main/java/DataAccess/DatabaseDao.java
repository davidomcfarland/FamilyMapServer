package DataAccess;

import model.*;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Database Data Access Object
 */
public class DatabaseDao {
    /**
     * SQL Database Connection
     */
    Connection conn;

    /**
     * Creates a Database DAO from iconn
     * @param iconn an existing SQL Database connection
     */
    DatabaseDao(Connection iconn){
        conn = iconn;
    }

    /**
     *  Interface with /clear
     *  Deletes ALL data from the database
     */
    public void clear() {}

    /**
     * Interface with /load
     * Clears all data from the database
     * Loads users, persons, and events into the database
     * @param users new Users for the database
     * @param persons new Persons for the database
     * @param events new Events for the database
     */
    public void load(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {}
}
