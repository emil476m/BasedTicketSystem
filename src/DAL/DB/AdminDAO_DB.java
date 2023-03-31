package DAL.DB;

import BE.*;
import DAL.DatabaseConnector;
import DAL.Interfaces.IAdminDAO;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO_DB implements IAdminDAO {

    private DatabaseConnector dbConnector;

    public AdminDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    @Override
    public User createUser(User user) throws Exception{
        String sql = "INSERT INTO User (PassWord, UserName, Mail, Name, UserType) VALUES (?,?,?,?,?);";

        try(Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){


            statement.setString(1, user.getPassWord());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
            statement.setInt(5, user.getUserType());

            statement.executeUpdate();

            //Get the generated Id from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            User newUser = null;

            if (user.getClass().getSimpleName() == Admin.class.getSimpleName())
                newUser = new Admin(id, user.getPassWord(), user.getUserName(), user.getMail(), user.getName());
            else if (user.getClass().getSimpleName() == Event_Coordinator.class.getSimpleName())
                newUser = new Event_Coordinator(id, user.getPassWord(), user.getUserName(), user.getMail(), user.getName());

            return newUser;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create User", e);
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        String sql = "DELETE FROM User WHERE Id = ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserID());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void assignEventToUser(User user, Event event) throws Exception {
        String sql = "INSERT INTO WorkingOnEvent (EventId, Event_CoordinatorId) VALUES (?, ?);";
        try(Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setInt(1, event.getId());
            statement.setInt(2, user.getUserID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create Event_Coordinator relations", e);
        }
    }

    private void deleteFromWorkingOnEvent(User user) throws Exception {
        String sql = "DELETE FROM WorkingOnEvent WHERE Event_CoordinatorId = ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserID());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getSimpleName(), e);
        }
    }


    @Override
    public void updateUser(User user) throws Exception {
        String sql = "UPDATE User SET PassWord = ?, Mail = ?, Name = ? WHERE Id = ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getPassWord());
            statement.setString(2, user.getMail());
            statement.setString(3, user.getName());
            statement.setInt(4, user.getUserID());
            //Run the specified SQL Statement
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to update playlist", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        String sql = "SELECT * FROM User;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            List<User> userList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String passWord = resultSet.getString("PassWord");
                String userName = resultSet.getString("UserName");
                String mail = resultSet.getString("Mail");
                String name = resultSet.getString("Name");
                int userTypes = resultSet.getInt("UserType");

                if (userTypes == 1){
                    userList.add(new Admin(id, passWord, userName, mail, name));
                } else if (userTypes == 2) {
                    userList.add(new Event_Coordinator(id, passWord, userName, mail, name));
                }
            }
            return userList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve Users", e);
        }
    }

    @Override
    public List<UserType> getAllUserTypes() throws Exception {
        String sql = "SELECT * FROM UserType;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            List<UserType> userTypeList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String type = resultSet.getString("Type");

                userTypeList.add(new UserType(id, type));
            }
            return userTypeList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve UserTypes", e);
        }
    }

    @Override
    public boolean checkUserName(String userName) throws Exception {
        String sql = "SELECT UserName FROM User WHERE UserName = ?;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userName1 = resultSet.getString("UserName");
                if (userName1.equals(userName))
                    return false;
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to check userName", e);
        }
    }
}
