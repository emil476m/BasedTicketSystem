package DAL.DB;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
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
        String tableName = "";
        if (user.getClass().getSimpleName() == Admin.class.getSimpleName())
            tableName = "Admins";
        else if (user.getClass().getSimpleName() == Event_Coordinator.class.getSimpleName())
            tableName = "Event_Coordinators";

        String sql = "INSERT INTO " + tableName + " (PassWord, UserName, Mail, Name) VALUES (?,?,?,?);";

        try(Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){


            statement.setString(1, user.getPassWord());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
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
        String tableName = "";
        if (user.getClass().getSimpleName() == Admin.class.getSimpleName())
            tableName = "Admins";
        else if (user.getClass().getSimpleName() == Event_Coordinator.class.getSimpleName())
            tableName = "Event_Coordinators";


        String sql = "DELETE FROM " + tableName + " WHERE Id = ?;";

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
        String tableName = "";
        if (user.getClass().getSimpleName() == Admin.class.getSimpleName())
            tableName = "Admins";
        else if (user.getClass().getSimpleName() == Event_Coordinator.class.getSimpleName())
            tableName = "Event_Coordinators";

        String sql = "UPDATE " + tableName + " SET PassWord = ?, Mail = ?, Name = ? WHERE Id = ?;";
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
    public List<User> getAllUsers(Class userType) throws Exception {
        if (userType == Admin.class){
            return getUserList("Admins", Admin.class);
        } else if (userType == Event_Coordinator.class) {
            return getUserList("Event_Coordinators", Event_Coordinator.class);
        }
        return null;
    }

    private List<User> getUserList(String userType, Class classType) throws Exception {
        String sql = "SELECT * FROM " + userType + ";";

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

                if (classType == Admin.class){
                    userList.add(new Admin(id, passWord, userName, mail, name));
                } else if (classType == Event_Coordinator.class) {
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
    public boolean checkUserName(String userName) throws Exception {
        String sql = "SELECT UserName FROM (SELECT UserName FROM Admins UNION ALL SELECT UserName FROM Event_Coordinators) a WHERE UserName = ?;";

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
