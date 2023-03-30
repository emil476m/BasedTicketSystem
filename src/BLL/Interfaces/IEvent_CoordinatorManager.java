package BLL.Interfaces;

import BE.Event;

import java.util.List;

public interface IEvent_CoordinatorManager {
    /**
     * gets a list of all events from the database
     * @return
     */
    List<Event> getAllevents() throws Exception;

    /**
     * sends an event to the database to be created
     * @param event
     * @return
     */
    Event createEvent(Event event) throws Exception;

    /**
     * updates an event to the database to get updated
     * @param event
     */
    void UpdateEvent(Event event) throws Exception;

    /**
     * deletes an event to the database to be deleted
     * @param event
     */
    void DeleteEvent(Event event) throws Exception;
    // ???
}
