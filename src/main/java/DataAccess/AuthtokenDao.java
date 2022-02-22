package DataAccess;

import model.Authtoken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Authtoken DAO
 */
public class AuthtokenDao {
    Connection conn;

    AuthtokenDao(Connection iconn){
        conn = iconn;
    }

    public void add(Authtoken newAuth) throws SQLException {
        String sql = "insert into Authtoken Values (\"" + newAuth.getAuthtoken() + "\", \"" + newAuth.getUsername() + "\")";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.executeUpdate();
        }
    }
}
