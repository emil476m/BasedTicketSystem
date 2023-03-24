package BLL;

import BE.Event;
import BLL.Interfaces.IEvent_CoordinatorManager;
import DAL.DB.EventCoordinatorDAO_DB;
import DAL.Interfaces.IEvent_CoordinatorDAO;

import java.io.IOException;
import java.util.List;

public class Event_CoordinatorManager implements IEvent_CoordinatorManager {
    IEvent_CoordinatorDAO eventCoordinatorDAO;
    public Event_CoordinatorManager() throws IOException {
        eventCoordinatorDAO = new EventCoordinatorDAO_DB();
    }

    /**
     * gets a list of all events
     * @return a list of all events
     * @throws Exception
     */
    @Override
    public List<Event> getAllevents() throws Exception {
       return eventCoordinatorDAO.getAllevents();
    }

    /**
     * sends an event to the database to be created
     * @param event
     * @return
     * @throws Exception
     */
    @Override
    public Event createEvent(Event event) throws Exception {
     return  eventCoordinatorDAO.createEvent(event);
    }

    /**
     * sends an event to the database to be updated
     * @param event
     * @throws Exception
     */
    @Override
    public void UpdateEvent(Event event) throws Exception {
        eventCoordinatorDAO.UpdateEvent(event);
    }

    /**
     * sends an event to the database to be deleted
     * @param event
     * @throws Exception
     */
    @Override
    public void DeleteEvent(Event event) throws Exception {
        eventCoordinatorDAO.DeleteEvent(event);
    }
}
