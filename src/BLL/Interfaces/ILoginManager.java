package BLL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;

public interface ILoginManager {

    Admin adminLogin(String Username, String Password) throws Exception;

    Event_Coordinator eventCoordinatorLogin(String Username, String Password) throws Exception;
}
