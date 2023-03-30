package BLL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;

import java.util.List;

public interface IAdminManager {

    User createUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;

    List<User> getAllUsers(Class userType) throws Exception;

    void updateUser(User user) throws Exception;

    boolean checkUserName(String userName) throws Exception;
}
