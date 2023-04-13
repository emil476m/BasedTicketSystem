package BLL.Interfaces;

import BE.Event;
import BE.Event_Coordinator;
import BE.Ticket;

import java.util.List;

public interface ITicketManager {
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
    Ticket craeteTicket(Ticket ticket, Event event, Event_Coordinator eventCoordinator) throws Exception;
}
