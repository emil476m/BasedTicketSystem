package GUI.Util;


import BE.Ticket;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.colorspace.PdfDeviceCs;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {

    Document document;
    String path ="QRCodeData//Ticket.pdf";
    List<File> QRcodes = new ArrayList<>();

    public PDFGenerator() throws FileNotFoundException {
      PdfWriter pdfWriter = new PdfWriter(path);
      PdfDocument pdfDocument = new PdfDocument(pdfWriter);
      pdfDocument.setDefaultPageSize(PageSize.A4);
      document = new Document(pdfDocument);
    }

    public void createTicket(Ticket ticket, List<File> qrCodes) throws IOException {
        QRcodes = qrCodes;
        for (File f: QRcodes)
        {
            Color color = new DeviceRgb(0,159,227);
            ImageData imageData = ImageDataFactory.create(f.getPath());
            Image qrcode = new Image(imageData);
            float towCol190 = 190f;
            float towCol = 285f;
            float towCol150 = towCol + 150f;
            float towColWidth[] = {towCol150, towCol};
            float fullWidth[] = {towCol190*3};
            Table table = new Table(towColWidth);
            table.addCell("Eventname: " + ticket.getEventName() + "\n" + "Name: " + ticket.getCostumerName() + "\t" + "Email: " + ticket.getCostumerEmail() + "\n" + "Row: " + ticket.getRow() + "\t" + "Seat: " + ticket.getSeat() + "\t" + "Ticket Type: " + ticket.getTicketType() + "\n" + "Location of Event: " + ticket.getLocation() + "\t" + "Date: " + ticket.getEventDate()).setBorder(Border.NO_BORDER);
            table.addCell(qrcode).setBorder(Border.NO_BORDER);
            Border bb = new SolidBorder(color, 2f);
            Table devider = new Table(fullWidth);
            devider.setBorder(bb);
            document.add(table);
            document.add(devider);
        }
        document.close();
    }
}
