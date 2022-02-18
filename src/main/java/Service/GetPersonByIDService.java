package Service;

import DataAccess.PersonDao;
import Request.Request;
import Response.Response;
import Response.GetPersonByIDResponse;

public class GetPersonByIDService extends Service{
    PersonDao personDao;
    @Override
    public Response serve(Request request){
        String id = "";
        return getPersonByID(id);
    }

    private GetPersonByIDResponse getPersonByID(String id){
        personDao.getPersonByID(id);
        return null;
    }
}
