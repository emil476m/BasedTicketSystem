package BLL;

import BE.Admin;
import BE.Event_Coordinator;
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
    public Admin adminLogin(String Username, String Password) throws Exception {
       return logInDAO.loginAdmin(Username,Password);
    }

    /**
     * sends two Strings to the Dal layer and gets an Event_Coordinator object that contains matching Strings text
     * @param Username
     * @param Password
     * @return returns an Event_Coordinator object which matches username and password
     * @throws Exception
     */
    @Override
    public Event_Coordinator eventCoordinatorLogin(String Username, String Password) throws Exception {
        return logInDAO.loginEventCoordinator(Username, Password);
    }
}
