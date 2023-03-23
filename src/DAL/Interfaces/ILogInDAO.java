package DAL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;

public interface ILogInDAO {

    Admin loginAdmin(String userName, String passWord) throws Exception;
    Event_Coordinator loginEventCoordinator(String userName, String passWord) throws Exception;
}
