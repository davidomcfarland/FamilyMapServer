package Service;

import DataAccess.UserDao;
import Request.Request;
import Response.Response;
import Response.RegisterResponse;
import model.User;

import java.sql.SQLException;

public class RegisterService extends Service{
    UserDao userDao;
    @Override
    public Response serve(Request request) throws SQLException {
        User newUser = null;
        return register(newUser);
    }

    private RegisterResponse register(User user) throws SQLException {
        userDao.registerUser(user);
        return null;
    }
}
