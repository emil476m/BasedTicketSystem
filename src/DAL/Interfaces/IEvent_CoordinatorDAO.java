package DAL.Interfaces;

import BE.Event;

import java.util.List;

public interface IEvent_CoordinatorDAO {
    /**
     * gets a list of all events from the database
     * @return
     */
    List<Event> getAllevents() throws Exception;

    /**
     * creates an event in the database
     *
     * @param event
     * @return
     */
    Event createEvent(Event event) throws Exception;

    /**
     * updates an event in the database
     * @param event
     */
    void UpdateEvent(Event event) throws Exception;

    /**
     * deletes an event form the database
     * @param event
     */
    void DeleteEvent(Event event) throws Exception;
}
