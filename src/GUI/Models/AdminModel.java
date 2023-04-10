package GUI.Models;

import BE.Event;
import BE.User;
import BE.UserType;
import BLL.AdminManager;
import BLL.Interfaces.IAdminManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {

    private IAdminManager adminManager;

    private List<User> allUsers;

    private ObservableList<User> userObservableList;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
        allUsers = new ArrayList<>();
        userObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<User> getUserObservableList() {
        return userObservableList;
    }

    public void createUser(User user) throws Exception{
        User newUser = adminManager.createUser(user);
        userObservableList.add(newUser);
        allUsers.add(newUser);
    }

    public void deleteUser(User user) throws Exception{
        userObservableList.remove(user);
        allUsers.remove(user);
        adminManager.deleteUser(user);
    }

    public void getAllUsers() throws Exception{
        List<User> eventCoordinatorList = adminManager.getAllUsers();
        userObservableList.addAll(eventCoordinatorList);
        allUsers.addAll(userObservableList);
    }

    public void updateUser(User updatedUser, User oldUser) throws Exception{
        userObservableList.remove(oldUser);
        allUsers.remove(oldUser);
        adminManager.updateUser(updatedUser);
        userObservableList.add(updatedUser);
        allUsers.add(updatedUser);
    }

    public boolean checkUserName(String userName) throws Exception{
        return adminManager.checkUserName(userName);
    }

    /**
     * Searches for users with given query.
     * @param query The query to search for.
     */
    public void searchUsers (String query) {
        if (allUsers.isEmpty())
            allUsers.addAll(userObservableList);
        else
            userObservableList.clear();

        for (User m: allUsers)
        {
            boolean nameContains = m.getName().toLowerCase().contains(query);


            boolean addMovie = nameContains ;

            if (addMovie) userObservableList.add(m);
        }
    }

    public User getLocalUserFromId(int id){
        for (User u: allUsers){
            if (u.getUserID() == id)
                return u;
        }
        return null;
    }

    /**
     * Clears the search query of Users.
     */
    public void clearSearch() {
        userObservableList.clear();
        userObservableList.addAll(allUsers);
    }

    public List<UserType> getAllUserTypes() throws Exception{
        return adminManager.getAllUserTypes();
    }

    /**
     * Deletes event and all its relations
     * @param event
     * @throws Exception
     */
    public void deleteEvent(Event event) throws Exception {
        adminManager.deleteEventRelations(event);
    }
}
