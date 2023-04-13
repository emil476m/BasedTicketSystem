package DAL.DB;

import BE.Event;
import BE.Event_Coordinator;
import BE.Ticket;
import DAL.DatabaseConnector;
import DAL.Interfaces.ITicketDAO;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO_DB implements ITicketDAO {

    private DatabaseConnector dbConnector;

    private LocalDate localDate;

    public TicketDAO_DB() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    /**
     * Gets a list of all Ticket from the Database
     * @return returns a list of all events
     * @throws Exception
     */
    @Override
    public List<Ticket> getAllTicket() throws Exception {
        String spl = "SELECT * FROM Ticket INNER JOIN TicketsToEvent on TicketsToEvents.TicketId = TicketId INNER JOIN [Event] on Event.Id = TicketsToEvents.TicketId;";
        try (Connection conn = dbConnector.getConnection())
        {
            PreparedStatement statement = conn.prepareStatement(spl);
            List<Ticket> tickets = new ArrayList<>();

            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String eventName = rs.getString("EventName");
                String costumerName = rs.getString("Name");
                String costumerEmail = rs.getString("Mail");
                String ticketType = rs.getString("TicketType");
                String location = rs.getString("EventLocation");
                LocalDate eventDate = rs.getDate("EventDate").toLocalDate();
                String ticketIssuedBy = rs.getString("TicketIssuedBy");

                Ticket ticket = new Ticket(id, eventName ,costumerName ,costumerEmail, ticketType, location,
                        eventDate, ticketIssuedBy );
                tickets.add(ticket);


            }
            return tickets;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw new Exception("failed to get events", e);
        }
    }

    /**
     * Creat
     * @param ticket
     * @return
     * @throws Exception
     */
    @Override
    public Ticket craeteTicket(Ticket ticket, Event event, Event_Coordinator eventCoordinator) throws Exception {
        String sql = "INSERT INTO Ticket(Name,TicketType,Mail,TicketIssuedBy) VALUES(?,?,?,?);";
        String sql2 = "INSERT INTO TicketsToEvent(EventId,TicketId) VALUES(?,?);";
        String sql3 = "UPDATE [Event] SET TicketAmount = ?, SpecialTicketAmount = ? WHERE id = ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            connection.setAutoCommit(false);

            statement.setString(1,ticket.getCostumerName());
            statement.setString(2, ticket.getTicketType());
            statement.setString(3, ticket.getCostumerEmail());
            statement.setInt(4, eventCoordinator.getUserID());

            ResultSet rs = statement.executeQuery();
            int id = 0;
            if (rs.next())
            {
                id = rs.getInt(1);
            }
            Ticket ticket1 = new Ticket(id,ticket.getEventName(), ticket.getCostumerName(), ticket.getCostumerEmail(),
                    ticket.getTicketType(), ticket.getLocation(), ticket.getEventDate(), eventCoordinator.getName());

            PreparedStatement relationtable = connection.prepareStatement(sql2);

            relationtable.setInt(1, event.getId());
            relationtable.setInt(2, ticket1.getId());
            relationtable.executeUpdate();


            PreparedStatement eventUpdate = connection.prepareStatement(sql3);

            eventUpdate.setInt(1, event.getTickets());
            eventUpdate.setInt(2, event.getSpecialTickets());
            eventUpdate.setInt(3, event.getId());
            eventUpdate.executeUpdate();

            connection.commit();
            return ticket1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
