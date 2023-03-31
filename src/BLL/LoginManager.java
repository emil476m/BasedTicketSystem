package BLL;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
import BLL.Interfaces.ILoginManager;
import DAL.DB.LoginDAO_DB;
import DAL.Interfaces.ILogInDAO;

import java.io.IOException;

public class LoginManager implements ILoginManager {

    ILogInDAO logInDAO;
    public LoginManager() throws IOException {
        logInDAO = new LoginDAO_DB();
    }

    /**
     * sends two Strings to the Dal layer and gets an Admin object that contains matching Strings text
     * @param Username
     * @param Password
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    @Override
    public User LoginUser(String Username, String Password) throws Exception {
       return logInDAO.loginUser(Username,Password);
    }
}
