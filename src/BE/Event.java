package BE;

import java.time.LocalDate;

public class Event {
    private int id;
    private int tickets;
    private int specialTickets;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private LocalDate eventDate;

    private int eventCreator;

    private String image;


    public Event(int id, String eventName, LocalDate eventDate, String eventLocation, int eventCreator, String eventDescription, int tickets, int specialTickets) {
        this.id = id;
        this.tickets = tickets;
        this.specialTickets = specialTickets;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventCreator = eventCreator;
    }

    public Event(String eventName, LocalDate eventDate, String eventLocation, int eventCreator, String eventDescription, int tickets, int specialTickets){
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventCreator = eventCreator;
        this.eventDescription = eventDescription;
        this.tickets = tickets;
        this.specialTickets = specialTickets;
    }

    public int getId() {
        return id;
    }

    public int getTickets() {
        return tickets;
    }

    public int getSpecialTickets() {
        return specialTickets;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public int getEventCreator() {
        return eventCreator;
    }

    public String getImage() {
        return image;
    }
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public void setSpecialTickets(int specialTickets) {
        this.specialTickets = specialTickets;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventCreator(int eventCreator) {
        this.eventCreator = eventCreator;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return  id + "\t" + eventName + "\t" + eventDate;
    }
}
