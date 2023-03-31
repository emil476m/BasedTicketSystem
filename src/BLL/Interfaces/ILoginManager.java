package BLL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;

public interface ILoginManager {
    /**
     * sends two Strings to the Dal layer and gets an Admin object that contains matching Strings text
     * @param Username
     * @param Password
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    User LoginUser(String Username, String Password) throws Exception;
}
