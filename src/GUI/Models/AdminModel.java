package GUI.Models;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
import BLL.AdminManager;
import BLL.Interfaces.IAdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class AdminModel {

    private IAdminManager adminManager;

    private ObservableList<User> userObservableList;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
        userObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<User> getUserObservableList() {
        return userObservableList;
    }

    public void createUser(User user) throws Exception{
        userObservableList.add(adminManager.createUser(user));
    }

    public void deleteUser(User user) throws Exception{
        userObservableList.remove(user);
        adminManager.deleteUser(user);
    }

    public void getAllUsers() throws Exception{
        List<User> eventCoordinatorList = adminManager.getAllUsers(Event_Coordinator.class);
        List<User> adminList = adminManager.getAllUsers(Admin.class);
        userObservableList.addAll(eventCoordinatorList);
        userObservableList.addAll(adminList);
    }

    public void updateUser(User updatedUser, User oldUser) throws Exception{
        userObservableList.remove(oldUser);
        adminManager.updateUser(updatedUser);
        userObservableList.add(updatedUser);
    }
}
