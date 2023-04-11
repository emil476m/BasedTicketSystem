package GUI.Models;

import BE.Event;
import BE.Event_Coordinator;
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
    public void createEvent(Event event, Event_Coordinator eventCoordinator) throws Exception {
        eventObservableList.add(eventCoordinatorManager.createEvent(event, eventCoordinator));
    }

    public void getAllEvents() throws Exception {
        List<Event> eventList = eventCoordinatorManager.getAllevents();
        eventObservableList.addAll(eventList);
    }
    public void UpdateEvent(Event updatedEvent, Event oldEvent) throws Exception {
        eventObservableList.remove(oldEvent);
        eventCoordinatorManager.UpdateEvent(updatedEvent);
        eventObservableList.add(updatedEvent);
    }

    public void removeEventFromLocal(Event event) {
        eventObservableList.remove(event);
    }
}
