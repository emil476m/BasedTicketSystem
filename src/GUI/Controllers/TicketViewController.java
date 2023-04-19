package GUI.Controllers;

import BE.Event;
import BE.Ticket;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TicketViewController extends BaseController{
    public Label lblEventName;
    public Label lblEventDate;
    public Label lblEventLocation;
    @FXML
    private BorderPane borderPaneTicket;
    @FXML
    private Button btnSendTicket, btnPrintTicket, btnReturn;
    @FXML
    private TextField txtfTicketType, txtCustomerName, txtCustomerEmail, txtfTicketAmount;
    @FXML
    private MenuItem menuItemBeerTicket,menuItemVIPTicket,menuItemNormalTicket;
    
    private Event event;

    @Override
    public void setup() {
        txtfTicketType.setEditable(false);
        if(event != null)
        {
            lblEventName.setText(event.getEventName());
            lblEventDate.setText(""+event.getEventDate());
            lblEventLocation.setText(event.getEventLocation());
        }
        else
        {
            lblEventName.setText("sell Special ticket");
            lblEventDate.setVisible(false);
            lblEventLocation.setVisible(false);
        }

    }

    @FXML
    private void dragScreen(){
        borderPaneTicket.setOnMousePressed(pressEvent -> {
            borderPaneTicket.setOnMouseDragged(dragEvent -> {
                borderPaneTicket.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneTicket.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void handleSendTicket(ActionEvent actionEvent) {
        
        if (checkTextFieldsNotNull()){
            String name = txtCustomerName.getText();
            String email = txtCustomerEmail.getText();
            String type = txtfTicketType.getText();
            int amount = Integer.parseInt(txtfTicketAmount.getText());

            Ticket newTicket = null;

            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This is not implemented");
                alert.showAndWait();
              /*  newTicket = new Ticket(event.getEventName(), name, email, type, event.getEventLocation(), event.getEventDate());

                if (newTicket.getTicketType().equals("Beer Ticket")){
                    event.setSpecialTickets(event.getSpecialTickets() - amount);
                }
                else{
                    event.setTickets(event.getTickets() - amount);
                }

                getModelsHandler().getTicketModel().createTicket(event, newTicket, getModelsHandler().getLoginModel().getLoggedinECoordinator(), amount);
                */
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionHandler.displayError(new Exception("Failed to Send a Ticket please try again", e));
            }
        }

        
        
    }

    private boolean checkTextFieldsNotNull() {
        if (checktxtTicketName() && checktxtemail() && checktxtTicketType() && checktxtTicketAmount())
            return true;
        else
            return false;
    }

    private boolean checktxtTicketType() {
        if (!txtfTicketType.getText().isEmpty() && txtfTicketType.getText() != null) return true;
        else
            return false;
    }

    private boolean checktxtemail() {
        if (!txtCustomerEmail.getText().isEmpty() && txtCustomerEmail.getText() != null) return true;
        else
            return false;
    }

    private boolean checktxtTicketName() {
        if (!txtCustomerName.getText().isEmpty() && txtCustomerName.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxtTicketAmount() {
        if (!txtfTicketAmount.getText().isEmpty() && txtfTicketAmount.getText() != null) return true;
        else
            return false;
    }

    public void handlePrintTicket(ActionEvent actionEvent) {
        try{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save tickets");
        fileChooser.setInitialFileName("ticket");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".pdf", "*.pdf"));
        {
            try
            {
                if(event == null)
                {
                    txtCustomerName.setText(" ");
                    txtCustomerEmail.setText(" ");
                }
                if(checkTextFieldsNotNull())
                {
                    String name = txtCustomerName.getText();
                    String email = txtCustomerEmail.getText();
                    String type = txtfTicketType.getText();
                    int amount = Integer.parseInt(txtfTicketAmount.getText());

                    Ticket newTicket = null;
                    if(event != null)
                    {
                        newTicket = new Ticket(event.getEventName(), name, email, type, event.getEventLocation(), event.getEventDate());
                    }
                    else
                    {
                        newTicket = new Ticket(" ",name,email,type," ",null);
                    }
                    if(event != null){
                        if (newTicket.getTicketType().equals("Beer Ticket")){
                        event.setSpecialTickets(event.getSpecialTickets() - amount);
                        }
                        else{
                            event.setTickets(event.getTickets() - amount);
                        }
                    }
                    getModelsHandler().getTicketModel().createTicket(event,newTicket,getModelsHandler().getLoginModel().getLoggedinECoordinator(),amount);
                    if(getModelsHandler().getTicketModel().getPDF() != null)
                    {
                    File file = fileChooser.showSaveDialog(btnPrintTicket.getScene().getWindow());
                    Files.copy(getModelsHandler().getTicketModel().getFile(), file.toPath());
                    }
            }}
            catch (IOException e) {
                ExceptionHandler.displayError(e);
            }
            catch (Exception e) {
                ExceptionHandler.displayError(e);
            }
        }
        } catch (Exception e)
        {
            AlertOpener.confirm("something went wrong", "please make sure that all text fields are filled");
        }
    }

    public void handleBeerTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemBeerTicket.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleVIPTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemVIPTicket.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleNormalTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemNormalTicket.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
