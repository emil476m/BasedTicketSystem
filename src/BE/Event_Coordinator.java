package BE;

public class Event_Coordinator extends User{
    //DEFAULT Event_Coordinator userType = 2
    public Event_Coordinator(int userID, String passWord, String userName, String mail, String name) {
        super(userID, passWord, userName, mail, name, 2);
    }

    public Event_Coordinator(String passWord, String userName, String mail, String name) {
        super(passWord, userName, mail, name, 2);
    }
}