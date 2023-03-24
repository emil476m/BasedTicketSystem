package BE;

public class Event {
    private int id;
    private int tickets;
    private int specialTickets;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventDate;
    private String eventCreator;


    public Event(int id, int tickets, int specialTickets, String eventName, String eventDescription, String eventLocation, String eventDate, String eventCreator) {
        this.id = id;
        this.tickets = tickets;
        this.specialTickets = specialTickets;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventCreator = eventCreator;
    }

    public Event(int tickets, int specialTickets, String eventName, String eventDescription, String eventLocation, String eventDate, String eventCreator) {
        this.tickets = tickets;
        this.specialTickets = specialTickets;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventCreator = eventCreator;
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

    public String getEventDate() {
        return eventDate;
    }

    public String getEventCreator() {
        return eventCreator;
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

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

}
