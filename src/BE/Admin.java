package BE;

public class Admin extends User{

    public Admin(int userID, String passWord, String userName, String mail, String name) {
        super(userID, passWord, userName, mail, name);
    }

    public Admin(String passWord, String userName, String mail, String name) {
        super(passWord, userName, mail, name);
    }
}
