package GUI.Models;

import BE.Admin;
import BE.Event_Coordinator;
import BLL.LoginManager;

import java.io.IOException;

public class LoginModel {
    LoginManager loginManager;
    Admin loggedInAdmin;

    Event_Coordinator loggedinECoordinator;

    public LoginModel() throws IOException {
        loginManager = new LoginManager();
    }

    /**
     * takes in two strings and gets either an Admin object or an Event_Coordinator object in return.
     * @param Username
     * @param Password
     * @throws Exception
     */
    public void loginAction(String Username, String Password) throws Exception
    {
        Admin admin = loginManager.adminLogin(Username,Password);
        if(admin != null)
        {
         loggedInAdmin = admin;
        } else if (loginManager.eventCoordinatorLogin(Username, Password) != null) {
            loggedinECoordinator = loginManager.eventCoordinatorLogin(Username, Password);
        }
    }

    public Admin getLoggedInAdmin(){return loggedInAdmin;}

    public Event_Coordinator getLoggedinECoordinator(){return loggedinECoordinator;}
}
