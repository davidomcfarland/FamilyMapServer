package Service;

import DataAccess.EventDao;
import Request.Request;
import Response.Response;
import Response.GetEventByIDResponse;

public class GetEventByIDService extends Service{
    EventDao eventDao;
    @Override
    public Response serve(Request request){
        String id = "";
        return getEventByID(id);
    }

    private GetEventByIDResponse getEventByID(String id){
        eventDao.getEventByID(id);
        return null;
    }
}
