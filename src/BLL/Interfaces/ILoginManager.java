package BLL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;

public interface ILoginManager {
    /**
     * sends two Strings to the Dal layer and gets an Admin object that contains matching Strings text
     * @param Username
     * @param Password
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    Admin adminLogin(String Username, String Password) throws Exception;

    /**
     * sends two Strings to the Dal layer and gets an Event_Coordinator object that contains matching Strings text
     * @param Username
     * @param Password
     * @return returns an Event_Coordinator object which matches username and password
     * @throws Exception
     */
    Event_Coordinator eventCoordinatorLogin(String Username, String Password) throws Exception;
}
