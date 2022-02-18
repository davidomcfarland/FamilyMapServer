package DataAccess;

import java.sql.Connection;

/**
 * Authtoken DAO - empty for now - use if needed
 */
public class AuthtokenDao {
    Connection conn;

    AuthtokenDao(Connection iconn){
        conn = iconn;
    }
}
