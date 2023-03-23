package DAL.DB;

import BE.Admin;
import BE.Event_Coordinator;
import DAL.DatabaseConnector;
import DAL.Interfaces.ILogInDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class LoginDAO_DB implements ILogInDAO {
private DatabaseConnector dbConnector;

public LoginDAO_DB() throws IOException {
    dbConnector = new DatabaseConnector();
}

    /**
     * Finds and returns an Admin object that contains text matching that of text from the String parameters
     * @param userName
     * @param passWord
     * @return returns an Admin object which matches username and password
     * @throws Exception
     */
    @Override
public Admin loginAdmin(String userName, String passWord) throws Exception {
    String sql = "SELECT * FROM Admins WHERE UserName=? AND PassWord =?;";
    Admin admin = null;
    try (Connection conn = dbConnector.getConnection(); PreparedStatement statement = conn.prepareStatement(sql))
    {
        statement.setString(1,userName);
        statement.setString(2,passWord);

        ResultSet rs = statement.executeQuery();
        while (rs.next())
        {
        int id = rs.getInt("Id");
        String PassWord = rs.getString("PassWord");
        String UserName = rs.getString("UserName");
        String Email = rs.getString("Mail");
        String Name = rs.getString("Name");
        admin = new Admin(id,PassWord,UserName,Email,Name);
        }
        if(admin != null)
        {
            return admin;
        }
        else{
            return null;
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
        throw new Exception("failed to login as admin", e);
    }
}

    /**
     * Finds and returns an Event_Coordinator object that contains text matching that of text from the String parameters
     * @param userName
     * @param passWord
     * @return returns an Event_Coordinator object which matches username and password
     * @throws Exception
     */
    @Override
public Event_Coordinator loginEventCoordinator(String userName, String passWord) throws Exception {
 String sql = "SELECT * FROM Event_Coordinators WHERE UserName=? AND PassWord=?";
    Event_Coordinator eventCoordinator = null;
 try (Connection conn = dbConnector.getConnection(); PreparedStatement statement = conn.prepareStatement(sql))
 {

    statement.setString(1, userName);
    statement.setString(2, passWord);

    ResultSet rs = statement.executeQuery();
    while (rs.next())
    {
    int id = rs.getInt("Id");
    String UserName = rs.getString("UserName");
    String PassWord = rs.getString("PassWord");
    String Email = rs.getString("Mail");
    String Name = rs.getString("Name");
    eventCoordinator = new Event_Coordinator(id,PassWord,UserName,Email,Name);
    }
    if(eventCoordinator != null)
    {
        return eventCoordinator;
    }
    else
    {
        return null;
    }
 }
 catch (SQLException e)
 {
     e.printStackTrace();
     throw new Exception("failed to login as Event Coordinator", e);
 }
}
}
