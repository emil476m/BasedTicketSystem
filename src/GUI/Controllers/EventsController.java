package GUI.Controllers;

import BE.Event;
import BE.Event_Coordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EventsController extends BaseController{
    @FXML
    private Label lblTextName;
    @FXML
    private Label lblTextMail;
    @FXML
    private Label lblTextAmountSold;
    @FXML
    private Label lblTextTicketsLeft;
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
    private Event openedEvent;


    @Override
    public void setup() {
        checkUserAndSetup();
        try {
            setEventInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkUserAndSetup(){
        if(getModelsHandler().getLoginModel().getLoggedinECoordinator() != null){
            setupEventCoordinator();
        }
        else if (getModelsHandler().getLoginModel().getLoggedInAdmin() != null){
            setupAdmin();
        }
    }

    private void getUsersForCheck() throws Exception {
        if (getModelsHandler().getAdminModel().getAllUsers().isEmpty() || getModelsHandler().getAdminModel().getAllUsers() == null){
            getModelsHandler().getAdminModel().retreiveAllUsers();
        }
    }

    private void setupAdmin(){
        lblClass.setText("Admin");
        txtCustomerName.setVisible(false);
        txtCustomerName.setEditable(false);
        txtCustomerEmail.setVisible(false);
        txtCustomerEmail.setEditable(false);
        lblTextName.setVisible(false);
        lblTextMail.setVisible(false);
        btnPrintTicket.setText("Delete Event");
    }

    private void setupEventCoordinator(){
        lblClass.setText("EventCoordinator");
        btnAssignCoordinator.setVisible(false);
        btnAssignCoordinator.setDisable(false);
    }

    private void setEventInfo() throws Exception {
        lblEventDate.setText(openedEvent.getEventDate().toString());
        lblEventName.setText(openedEvent.getEventName());
        lblEventLocation.setText(openedEvent.getEventLocation());
        getUsersForCheck();
        String name = getModelsHandler().getAdminModel().getLocalUserFromId(openedEvent.getEventCreator()).getName();
        if (name != null)
            lblEventCreator.setText(name);
    }

    public void setOpenedEvent(Event openedEvent) {
        this.openedEvent = openedEvent;
    }

    public void handleLogOut() {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();
    }

    public void handlePrintTicket(ActionEvent event) {
        try {
            if (btnPrintTicket.getText().equals("Delete Event")) {
                deleteEvent();
            } else if (btnPrintTicket.getText().equals("Print")) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteEvent() throws Exception {
        getModelsHandler().getEventCoordinatorModel().removeEventFromLocal(openedEvent);
        getModelsHandler().getAdminModel().deleteEvent(openedEvent);
        handleLogOut();
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
