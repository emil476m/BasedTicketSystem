package DAL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;

public interface ILogInDAO {

    /**
     * Finds and returns an Admin object that contains text matching that of text from the String parameters
     * @param userName
     * @param passWord
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    Admin loginAdmin(String userName, String passWord) throws Exception;

    /**
     * Finds and returns an Event_Coordinator object that contains text matching that of text from the String parameters
     * @param userName
     * @param passWord
     * @return returns an Event_Coordinator object which matches username and password
     * @throws Exception
     */
    Event_Coordinator loginEventCoordinator(String userName, String passWord) throws Exception;
}
