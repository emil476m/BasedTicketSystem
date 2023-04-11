package GUI.Util;

import BE.Ticket;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    /**
     * creates the email and sends it to the costumer
     * @param ticket
     * @param file
     */
    public void sendEmail(Ticket ticket, File file)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eventmasterbot1@gmail.com", "EventMaster32");
            }
        });

        Message message = new MimeMessage(session);

        try {
            message.setSubject("Event ticket from Event Master");
            message.setText("Your ticket for the event " + ticket.getEventName() + " is in the .pdf file, if you have anyquestions feel free to ask an event coordinator");

            Address address = new InternetAddress(ticket.getCostumerEmail());
            message.setRecipient(Message.RecipientType.TO, address);

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
