package Service;

import DataAccess.UserDao;
import Request.Request;
import Response.Response;
import Response.FillResponse;

public class FillService extends Service{
    UserDao userDao;
    @Override
    public Response serve(Request request){return fill("", 0);}

    private FillResponse fill(String username, int generations){
        userDao.fillUser(username, generations);
        return null;
    }
}
