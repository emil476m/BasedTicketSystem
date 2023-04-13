package DAL.DB;

import BE.User;
import DAL.DatabaseConnector;
import DAL.Interfaces.ILogInDAO;
import DAL.Util.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO_DB implements ILogInDAO {
private DatabaseConnector dbConnector;

    public LoginDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    @Override
    public User loginUser(String userName, String passWord) throws Exception {
        String sql = "SELECT * FROM [User] Where UserName=?";
        User user = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
                 statement.setString(1, userName);

                 ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String PassWord = resultSet.getString("PassWord");
                String UserName = resultSet.getString("UserName");
                String mail = resultSet.getString("Mail");
                String name = resultSet.getString("Name");
                int userTypes = resultSet.getInt("UserType");
                user = new User(id,PassWord,UserName,mail,name,userTypes);
        }
            if(BCrypt.checkpw(passWord,user.getPassWord()))
            {
            return user;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("Failed to login");
        }
        return null;
    }
}
