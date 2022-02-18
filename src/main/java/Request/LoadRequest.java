package Request;

import model.Event;
import model.Person;
import model.User;

import java.util.List;

public class LoadRequest extends Request {
    List<User> users;
    List<Person> persons;
    List<Event> events;
}
