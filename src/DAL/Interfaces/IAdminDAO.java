package DAL.Interfaces;

import BE.Admin;
import BE.Event_Coordinator;

public interface IAdminDAO {


    Admin createAdmin(Admin admin) throws Exception;
    Event_Coordinator createEvent_Coordinator(Event_Coordinator event_coordinator) throws Exception;
}
