package Service;

import DataAccess.PersonDao;
import Request.Request;
import Response.Response;
import Response.GetPersonByIDResponse;

import java.sql.SQLException;

public class GetPersonByIDService extends Service{
    PersonDao personDao;
    @Override
    public Response serve(Request request) throws SQLException {
        String id = "";
        return getPersonByID(id);
    }

    private GetPersonByIDResponse getPersonByID(String id) throws SQLException {
        personDao.getPersonByID(id);
        return null;
    }
}
