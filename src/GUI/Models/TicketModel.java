package GUI.Models;

import BE.Ticket;
import GUI.Util.QrCodeGenerator;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TicketModel {

    List<File> qrcodes;

  public void generateQRCodes(int amount, Ticket ticket) throws IOException, WriterException {
      QrCodeGenerator qr = new QrCodeGenerator();
      for(int i = 0; i <= amount; i++)
      {
          qr.generateQrCode(ticketData(ticket), qr.getFilePath(), qr.getCharSet(), qr.getMap(),200,200);
      }
      qrcodes = qr.getQRCodes();
  }

  private String ticketData(Ticket ticket)
  {
      String data ="Eventname: " + ticket.getEventName() + "\n" + "Name: " + ticket.getCostumerName() + "\t" + "Email: " + ticket.getCostumerEmail() + "\n" + "Row: " + ticket.getRow() + "\t" + "Seat: " + ticket.getSeat() + "\t" + "Ticket Type: " + ticket.getTicketType() +  "\n" + "Location of Event: " + ticket.getLocation() + "\t" + "Date: " + ticket.getEventDate();
      return data;
  }
}
