package Service;

import DataAccess.DatabaseDao;
import Request.Request;
import Response.Response;
import Response.LoadResponse;
import model.Event;
import model.Person;
import model.User;

import java.util.ArrayList;

public class LoadService extends Service{
    DatabaseDao database;
    @Override
    public Response serve(Request request){
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        ArrayList<Event> events = new ArrayList<>();

        return fill(users, persons, events);
    }

    private LoadResponse fill(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events){
        database.load(users, persons, events);
        return null;
    }
}
