package BLL;

import BE.Event;
import BE.User;
import BLL.Interfaces.IAdminManager;
import DAL.DB.AdminDAO_DB;
import DAL.Interfaces.IAdminDAO;

import java.io.IOException;
import java.util.List;

public class AdminManager implements IAdminManager {

    private IAdminDAO databaseAcces;

    public AdminManager() throws IOException {
        databaseAcces = new AdminDAO_DB();
    }

    @Override
    public User createUser(User user) throws Exception{
        return databaseAcces.createUser(user);
    }

    @Override
    public void deleteUser(User user) throws Exception{
        databaseAcces.deleteUser(user);
    }

    @Override
    public List<User> getAllUsers(Class userType) throws Exception {
        return databaseAcces.getAllUsers(userType);
    }
    @Override
    public void updateUser(User user) throws Exception{
        databaseAcces.updateUser(user);
    }

    @Override
    public boolean checkUserName(String userName) throws Exception{
        return databaseAcces.checkUserName(userName);
    }

    @Override
    public void assignEventToUser(User user, Event event) throws Exception{
        databaseAcces.assignEventToUser(user, event);
    }
}
