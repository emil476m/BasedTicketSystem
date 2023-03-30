package GUI.Models;

import BE.Event;
import BLL.Event_CoordinatorManager;
import BLL.Interfaces.IEvent_CoordinatorManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class EventCoordinatorModel {

    private IEvent_CoordinatorManager eventCoordinatorManager;

    private ObservableList<Event> eventObservableList;

    public EventCoordinatorModel() throws IOException {
        eventCoordinatorManager = new Event_CoordinatorManager();
        eventObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<Event> getEventObservableList() {
        return eventObservableList;
    }
    public void createEvent(Event event) throws Exception {
        eventObservableList.add(eventCoordinatorManager.createEvent(event));
    }

    public void getAllEvents(Event event) throws Exception {
        List<Event> eventList = eventCoordinatorManager.getAllevents();
        eventObservableList.addAll(eventList);
    }
    public void UpdateEvent(Event updatedEvent, Event oldEvent) throws Exception {
        eventObservableList.remove(oldEvent);
        eventCoordinatorManager.UpdateEvent(updatedEvent);
        eventObservableList.add(updatedEvent);
    }
}