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
    public Admin createAdmin(Admin admin) throws Exception{
        String sql = "INSERT INTO Admins (PassWord, UserName, Mail, Name) VALUES (?,?,?,?);";

        try(Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){


            statement.setString(1, admin.getPassWord());
            statement.setString(2, admin.getUserName());
            statement.setString(3, admin.getMail());
            statement.setString(4, admin.getName());
            statement.executeUpdate();

            //Get the generated Id from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            Admin newAdmin = new Admin(id, admin.getPassWord(), admin.getUserName(), admin.getMail(), admin.getName());
            return newAdmin;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create Admin", e);
        }
    }

    @Override
    public Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception{
        String sql = "INSERT INTO Event_Coordinators (PassWord, UserName, Mail, Name) VALUES (?,?,?,?);";

        try(Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){


            statement.setString(1, event_coordinator.getPassWord());
            statement.setString(2, event_coordinator.getUserName());
            statement.setString(3, event_coordinator.getMail());
            statement.setString(4, event_coordinator.getName());
            statement.executeUpdate();

            //Get the generated Id from the DB
            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;

            if(rs.next()){
                id = rs.getInt(1);
            }

            Event_Coordinator newEvent_Coordinator = new Event_Coordinator(id, event_coordinator.getPassWord(), event_coordinator.getUserName(), event_coordinator.getMail(), event_coordinator.getName());
            return newEvent_Coordinator;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create Event_Coordinator", e);
        }
    }

    @Override
    public void deleteUser(User user) throws Exception {
        String tableName = "";
        if (user.getClass().getName() == Admin.class.getName())
            tableName = "Admins";
        else
            tableName = "Event_Coordinators";


        String sql = "DELETE FROM " + tableName + " WHERE Id = ?;";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUserID());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove " + user.getClass().getName(), e);
        }
    }


    public void updateUser(User user) throws Exception {
        String tableName = "";
        if (user.getClass().getName() == Admin.class.getName())
            tableName = "Admins";
        else
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
}
