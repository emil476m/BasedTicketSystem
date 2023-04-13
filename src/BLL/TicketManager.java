package BLL;

import BE.Event;
import BE.Event_Coordinator;
import BE.Ticket;
import BLL.Interfaces.ITicketManager;
import DAL.DB.TicketDAO_DB;
import DAL.Interfaces.ITicketDAO;

import java.io.IOException;
import java.util.List;

public class TicketManager implements ITicketManager {

    private ITicketDAO ticketDAO;

    public TicketManager() throws IOException{
        ticketDAO = new TicketDAO_DB();
    }

    @Override
    public List<Ticket> getAllTicket() throws Exception {
        return ticketDAO.getAllTicket();
    }

    @Override
    public Ticket craeteTicket(Ticket ticket, Event event, Event_Coordinator eventCoordinator) throws Exception {
        return ticketDAO.craeteTicket(ticket, event, eventCoordinator);
    }
}
