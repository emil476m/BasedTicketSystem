package DAL.Interfaces;

import BE.Event;
import BE.Event_Coordinator;
import BE.Ticket;

import java.util.List;

public interface ITicketDAO {
    /**
     * gets a list of all the ticket from the database
     * @return
     * @throws Exception
     */
    List<Ticket> getAllTicket() throws Exception;

    /**
     * @param ticket
     * @return
     * @throws Exception
     */
    Ticket craeteTicket(Ticket ticket,Event event, Event_Coordinator eventCoordinator) throws Exception;

    /**
     * creates a ticket without the event
     * @param ticket
     * @param eventCoordinator
     * @return
     * @throws Exception
     */
    Ticket craeteTicketWithoutEvent(Ticket ticket, Event_Coordinator eventCoordinator) throws Exception;

}
