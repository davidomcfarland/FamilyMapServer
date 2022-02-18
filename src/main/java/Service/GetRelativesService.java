package Service;

import DataAccess.PersonDao;
import Request.Request;
import Response.Response;
import Response.GetRelativesResponse;

public class GetRelativesService extends Service{
    PersonDao personDao;

    @Override
    public Response serve(Request request){return getRelatives();}

    private GetRelativesResponse getRelatives(){return null;}
}
