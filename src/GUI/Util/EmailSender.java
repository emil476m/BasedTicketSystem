package GUI.Util;

import BE.Ticket;
import com.sun.mail.smtp.SMTPTransport;

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
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "Smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eventmasterbot1@gmail.com", "eqnyghcqqhidjnec");
            }
        });

        Message message = new MimeMessage(session);

        try {
            System.out.println("trying to send email");
            message.setSubject("Event ticket from Event Master");
            message.setText("Your ticket for the event " + ticket.getEventName() + " is in the .pdf file, if you have anyquestions feel free to ask an event coordinator");

            Address address = new InternetAddress(ticket.getCostumerEmail());
            message.setFrom(new InternetAddress("eventmasterbot1@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, address);

            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(file);
            multipart.addBodyPart(attachment);
            System.out.println("almost there");
            message.setContent(multipart);
            SMTPTransport transport = (SMTPTransport) session.getTransport();
            transport.connect();
            if(transport.isConnected()){
                transport.sendMessage(message,message.getAllRecipients());
            System.out.println("email was sent");}
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
