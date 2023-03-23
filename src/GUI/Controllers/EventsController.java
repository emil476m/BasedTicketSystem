package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EventsController extends BaseController{
    public BorderPane borderPaneEvent;
    public Label lblClass;
    public Button btnAssignCoordinator;
    public TextField txtCustomerName;
    public TextField txtCustomerEmail;
    public Button btnPrintTicket;
    public Label lblTicketSold;
    public Label lblTicketLift;
    public ImageView imgEvent;
    public ListView lvAssignCoordinator;
    public Label lblEventName;
    public Label lblEventDate;
    public Label lblEventCreator;
    public Label lblEventLocation;
    public Button btnLogOut;
    public Button btnReturn;

    @Override
    public void setup() {

    }

    public void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    public void handlePrintTicket(ActionEvent event) {
    }

    public void handleAssignCoordinator(ActionEvent event) {
    }

    public void handleReturn(ActionEvent event) {
    }

    @FXML
    private void dragScreen(){
        borderPaneEvent.setOnMousePressed(pressEvent -> {
            borderPaneEvent.setOnMouseDragged(dragEvent -> {
                borderPaneEvent.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneEvent.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }


}
