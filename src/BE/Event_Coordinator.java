package BE;

public class Event_Coordinator extends User{
    public Event_Coordinator(int userID, String passWord, String userName, String mail, String name) {
        super(userID, passWord, userName, mail, name);
    }

    public Event_Coordinator(String passWord, String userName, String mail, String name) {
        super(passWord, userName, mail, name);
    }
}