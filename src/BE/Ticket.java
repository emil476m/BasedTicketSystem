package BE;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private String eventName;
    private String costumerName;
    private String costumerEmail;
    private String ticketType;
    private String location;
    private LocalDate eventDate;
    private String ticketIssuedBy;
    public Ticket(String eventName, String costumerName, String costumerEmail, String ticketType , String location, LocalDate eventDate) {
        this.eventName = eventName;
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.ticketType = ticketType;
        this.location = location;
        this.eventDate = eventDate;
    }

    public Ticket(int id, String eventName, String costumerName, String costumerEmail,
                  String ticketType , String location, LocalDate eventDate, String ticketIssuedBy) {
        this.id = id;
        this.eventName = eventName;
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.ticketType = ticketType;
        this.location = location;
        this.eventDate = eventDate;
        this.ticketIssuedBy = ticketIssuedBy;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public String getCostumerEmail() {
        return costumerEmail;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }
}
