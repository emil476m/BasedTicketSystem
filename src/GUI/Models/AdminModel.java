package GUI.Models;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;
import BLL.AdminManager;
import BLL.Interfaces.IAdminManager;
import DAL.DB.AdminDAO_DB;
import DAL.Interfaces.IAdminDAO;

import java.io.IOException;

public class AdminModel {

    private IAdminManager adminManager;

    public AdminModel() throws IOException {
        adminManager = new AdminManager();
    }

    public Admin createAdmin(Admin admin) throws Exception{
        return adminManager.createAdmin(admin);
    }

    public Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception{
        return adminManager.createEvent_Coordinator(event_coordinator);
    }

    public void deleteUser(User user) throws Exception{
        adminManager.deleteUser(user);
    }
}
