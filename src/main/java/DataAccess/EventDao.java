package DataAccess;

import model.Event;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Event Data Access Object
 */
public class EventDao {
    Connection conn;

    /**
     * Creates an EventDAO from iconn
     * @param iconn An existing SQL database connection
     */
    EventDao(Connection iconn) {
        conn = iconn;
    }

    /**
     * Interface with /event/[eventID] API
     * @param EventID A string representing the eventID
     * @return Returns the single Event object with the specified ID (if the event is associated with the current user).
     */
    public Event getEventByID(String EventID){return null;}

    /**
     * Interface with /event API
     * @return Returns ALL events for ALL family members of the current user.
     */
    public ArrayList<Event> getAllRelativeEvents(){return null;}
}
