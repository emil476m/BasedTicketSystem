package GUI.Models;

import BE.Event;
import BE.Event_Coordinator;
import BE.Ticket;
import BLL.Interfaces.ITicketManager;
import BLL.TicketManager;
import GUI.Util.PDFGenerator;
import GUI.Util.QrCodeGenerator;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TicketModel {

    private ITicketManager ticketManager;

    private PDFGenerator pdfGenerator;

    private  List<File> qrcodes;

    File file = new File("QRCodeData//Ticket.pdf");

    public TicketModel() throws IOException {
        ticketManager = new TicketManager();
        pdfGenerator = new PDFGenerator();
        qrcodes = new ArrayList<>();
    }

    public void createTicket(Event event, Ticket ticket, Event_Coordinator eventCoordinator, int amount) throws Exception {
        Ticket ticket1 = null;
        if(!qrcodes.isEmpty())
        {
            qrcodes.clear();
        }
        if(ticket.getEventDate() != null)
        {
            ticket1 = ticketManager.craeteTicket(ticket,event, eventCoordinator);
        }
        else
        {
            ticket1 = ticketManager.craeteTicketWithoutEvent(ticket,eventCoordinator);
        }
            generateQRCodes(amount, ticket);
        if (ticket1 != null){
            pdfGenerator.createTicket(ticket,qrcodes);
        }
    }

    public void getAllTicket() throws Exception {
        ticketManager.getAllTicket();
    }


    /**
     * generates qrcodes for tickets
     * @param amount
     * @param ticket
     * @throws IOException
     * @throws WriterException
     */
  public void generateQRCodes(int amount, Ticket ticket) throws IOException, WriterException {
      QrCodeGenerator qr = new QrCodeGenerator();
      qr.generateQrCode(ticketData(ticket), qr.getCharSet(), qr.getMap(),100,100, amount);
      qrcodes = qr.getQRCodes();
  }

  private String ticketData(Ticket ticket)
  {
      String data ="Eventname: " + ticket.getEventName() + "\n" + "Name: " + ticket.getCostumerName() + "\t" + "Email: " + ticket.getCostumerEmail() + "\n" + "Ticket Type: " + ticket.getTicketType() +  "\n" + "Location of Event: " + ticket.getLocation() + "\t" + "Date: " + ticket.getEventDate();
      return data;
  }

    public Path getFile() {
        return file.toPath();
    }

    public File getPDF()
    {
        return file;
    }
}
