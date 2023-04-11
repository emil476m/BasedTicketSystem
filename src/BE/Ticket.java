package BE;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private String eventName;
    private String costumerName;
    private String costumerEmail;
    private int row;
    private int seat;
    private String ticketType;
    private String location;
    private LocalDate eventDate;

    public Ticket(String eventName, String costumerName, String costumerEmail, int row, int seat, String ticketType , String location, LocalDate eventDate) {
        this.eventName = eventName;
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.row = row;
        this.seat = seat;
        this.ticketType = ticketType;
        this.location = location;
        this.eventDate = eventDate;
    }

    public Ticket(int id, String eventName, String costumerName, String costumerEmail, int row, int seat, String ticketType , String location, LocalDate eventDate) {
        this.id = id;
        this.eventName = eventName;
        this.costumerName = costumerName;
        this.costumerEmail = costumerEmail;
        this.row = row;
        this.seat = seat;
        this.ticketType = ticketType;
        this.location = location;
        this.eventDate = eventDate;
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

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
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
