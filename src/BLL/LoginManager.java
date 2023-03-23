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

    @Override
    public Admin adminLogin(String Username, String Password) throws Exception {
       return logInDAO.loginAdmin(Username,Password);
    }

    @Override
    public Event_Coordinator eventCoordinatorLogin(String Username, String Password) throws Exception {
        return logInDAO.loginEventCoordinator(Username, Password);
    }
}
