package BE;

public class Admin extends User{
    //DEFAULT ADMIN userType = 1
    public Admin(int userID, String passWord, String userName, String mail, String name) {

        super(userID, passWord, userName, mail, name, 1);
    }

    public Admin(String passWord, String userName, String mail, String name) {
        super(passWord, userName, mail, name, 1);
    }
}
