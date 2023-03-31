package DAL.Interfaces;

import BE.Event;
import BE.User;

import java.util.List;

public interface IAdminDAO {


    User createUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;
    List<User> getAllUsers(Class userType) throws Exception;

    void assignEventToUser(User user, Event event) throws Exception;

    void updateUser(User user) throws Exception;

    boolean checkUserName(String userName) throws Exception;

}
