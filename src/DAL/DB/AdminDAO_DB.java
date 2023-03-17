package DAL.DB;

import BE.Admin;
import BE.Event_Coordinator;
import DAL.DatabaseConnector;
import DAL.Interfaces.IAdminDAO;

import java.io.IOException;
import java.sql.*;

public class AdminDAO_DB implements IAdminDAO {

    private DatabaseConnector dbConnector;

    public AdminDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }


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

    public Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception{
        String sql = "INSERT INTO Event_Coordinator (PassWord, UserName, Mail, Name) VALUES (?,?,?,?);";

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
}
