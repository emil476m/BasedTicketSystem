package BLL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;
import BE.User;

public interface IAdminManager {

    Admin createAdmin(Admin admin) throws Exception;

    Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception;

    public void deleteUser(User user) throws Exception;
}
