package GUI.Controllers;

import BE.Event_Coordinator;
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
    @FXML
    private BorderPane borderPaneEvent;
    @FXML
    private Label lblClass;
    @FXML
    private Button btnAssignCoordinator;
    @FXML
    private TextField txtCustomerName;
    @FXML
    private TextField txtCustomerEmail;
    @FXML
    private Button btnPrintTicket;
    @FXML
    private Label lblTicketSold;
    @FXML
    private Label lblTicketLift;
    @FXML
    private ImageView imgEvent;
    @FXML
    private ListView<Event_Coordinator> lvAssignCoordinator;
    @FXML
    private Label lblEventName;
    @FXML
    private Label lblEventDate;
    @FXML
    private Label lblEventCreator;
    @FXML
    private Label lblEventLocation;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnReturn;

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
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();

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
