package Service;

import DataAccess.EventDao;
import Request.Request;
import Response.Response;
import Response.GetAllEventsResponse;

public class GetAllEventsService extends Service{
    EventDao eventDao;
    @Override
    public Response serve(Request request){return GetAllEvents();}

    private GetAllEventsResponse GetAllEvents() {
        eventDao.getAllRelativeEvents();
        return null;
    }
}
