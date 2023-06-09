package DAL.DB;

import BE.Event;
import BE.Event_Coordinator;
import DAL.DatabaseConnector;
import DAL.Interfaces.IEvent_CoordinatorDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorDAO_DB implements IEvent_CoordinatorDAO {
    private DatabaseConnector dbConnector;
    private LocalDate LocalDate;

    public EventCoordinatorDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }
    /**
     * Gets a list of all evnets form the database
     * @return returns a list of all events
     */
    @Override
    public List<Event> getAllevents() throws Exception {
        String sql = "SELECT * FROM Event";
        try(Connection conn = dbConnector.getConnection())
        {
            PreparedStatement statement = conn.prepareStatement(sql);
            List<Event> events = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("EventName");
                int ticketAmount = rs.getInt("TicketAmount");
                int specialticketAmount = rs.getInt("SpecialTicketAmount");
                LocalDate date = rs.getDate("EventDate").toLocalDate();
                String eventLocation = rs.getString("EventLocation");
                String eventDescription = rs.getString("EventDescription");
                int eventCreator = rs.getInt("EventCreator");
                Event event = new Event(id,name,date, eventLocation, eventCreator, eventDescription, ticketAmount, specialticketAmount);
                events.add(event);
            }
            return events;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            throw new Exception("failed to get all events", e);
        }
    }

    /**
     * creates an event in the database
     *
     * @param event
     * @return
     */
    @Override
    public Event createEvent(Event event, Event_Coordinator eventCoordinator) throws Exception {
        String sql = "INSERT INTO Event(EventName,TicketAmount,SpecialTicketAmount,EventDate,EventLocation,EventDescription,EventCreator) VALUES(?,?,?,?,?,?,?);";
        try (Connection connection = dbConnector.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1,event.getEventName());
            statement.setInt(2,event.getTickets());
            statement.setInt(3,event.getSpecialTickets());
            statement.setDate(4, (java.sql.Date.valueOf(event.getEventDate())));
            statement.setString(5, event.getEventLocation());
            statement.setString(6, event.getEventDescription());
            statement.setInt(7, eventCoordinator.getUserID());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            int id = 0;
            if(rs.next())
            {
                id = rs.getInt(1);
            }

            Event event1 = new Event(id,event.getEventName()
                    ,event.getEventDate(), event.getEventLocation(), event.getEventCreator(), event.getEventDescription(), event.getTickets(), event.getSpecialTickets());
            return event1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            //throw new Exception("failed to create event");
            return null;
        }
    }

    /**
     * updates an event in the database
     * @param event
     */
    @Override
    public void updateEvent(Event event) throws Exception{
        String sql = "UPDATE [Event] SET EventName=?, TicketAmount=?, SpecialTicketAmount=?, EventDate=?, EventLocation=?, EventDescription=?, EventCreator=? WHERE Id=?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1,event.getEventName());
            statement.setInt(2,event.getTickets());
            statement.setInt(3,event.getSpecialTickets());
            statement.setDate(4, (java.sql.Date.valueOf(event.getEventDate())));
            statement.setString(5,event.getEventLocation());
            statement.setString(6,event.getEventDescription());
            statement.setInt(7, event.getEventCreator());
            statement.setInt(8,event.getId());

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("failed to update event",e);
        }
    }

    /**
     * deletes an event from the database
     * @param event
     */
    @Override
    public void DeleteEvent(Event event) throws Exception
    {
        String sql = "DELETE FROM Events Where Id=?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1,event.getId());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("failed to delete event");
        }
    }
}
