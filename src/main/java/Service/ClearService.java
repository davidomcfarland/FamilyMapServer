package Service;

import DataAccess.DatabaseDao;
import Request.ClearRequest;
import Request.Request;
import Response.ClearResponse;
import Response.Response;

public class ClearService extends Service{
    DatabaseDao database;

    @Override
    public Response serve(Request request){return clear();}

    private ClearResponse clear(){
        database.clear();
        return null;
    }
}
