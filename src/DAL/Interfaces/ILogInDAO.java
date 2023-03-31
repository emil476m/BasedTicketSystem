package DAL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;

public interface ILogInDAO {

    /**
     * Finds and returns a User object that contains text matching that of text from the String parameters
     *
     * @param userName
     * @param passWord
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    User loginUser(String userName, String passWord) throws Exception;

}


