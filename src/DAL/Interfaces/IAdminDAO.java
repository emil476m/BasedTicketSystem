package DAL.Interfaces;

import BE.Event;
import BE.User;
import BE.UserType;

import java.util.List;

public interface IAdminDAO {


    User createUser(User user) throws Exception;

    public void deleteUser(User user) throws Exception;

    List<Integer> getUsersWorkingOnEvent(Event event) throws Exception;

    void assignEventToUser(User user, Event event) throws Exception;

    void deleteFromWorkingOnEvent(User user, Event event) throws Exception;

    void updateUser(User user) throws Exception;

    List<User> getAllUsers() throws Exception;

    List<UserType> getAllUserTypes() throws Exception;

    boolean checkUserName(String userName) throws Exception;

    void deleteEventRelations(Event event) throws Exception;
}
