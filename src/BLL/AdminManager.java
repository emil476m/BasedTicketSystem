package BLL;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
import BLL.Interfaces.IAdminManager;
import DAL.DB.AdminDAO_DB;
import DAL.Interfaces.IAdminDAO;

import java.io.IOException;

public class AdminManager implements IAdminManager {

    private IAdminDAO databaseAcces;

    public AdminManager() throws IOException {
        databaseAcces = new AdminDAO_DB();
    }

    @Override
    public Admin createAdmin(Admin admin) throws Exception{
        return databaseAcces.createAdmin(admin);
    }

    @Override
    public Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception{
        return databaseAcces.createEvent_Coordinator(event_coordinator);
    }

    @Override
    public void deleteUser(User user) throws Exception{
        databaseAcces.deleteUser(user);
    }
}
