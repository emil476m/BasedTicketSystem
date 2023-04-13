package GUI.Models;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
import BLL.Interfaces.ILoginManager;
import BLL.LoginManager;

import java.io.IOException;

public class LoginModel {
    ILoginManager loginManager;
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
      User user = loginManager.LoginUser(Username,Password);
      if(user.getUserType() == 1)
      {
          loggedInAdmin = new Admin(user.getUserID(),user.getPassWord(),user.getUserName(),user.getMail(), user.getName());
      } else if (user.getUserType()==2)
      {
        loggedinECoordinator = new Event_Coordinator(user.getUserID(),user.getPassWord(),user.getUserName(),user.getMail(), user.getName());
      }
    }

    public Admin getLoggedInAdmin(){return loggedInAdmin;}

    public Event_Coordinator getLoggedinECoordinator(){return loggedinECoordinator;}
}
