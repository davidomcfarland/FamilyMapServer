package Service;

import Request.Request;
import Response.Response;

import java.sql.SQLException;

/**
 * Base class for common methods and attributes of all Service Objects
 */
public abstract class Service {
    abstract Response serve(Request request) throws SQLException;
}
