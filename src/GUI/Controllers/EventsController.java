package GUI.Controllers;

import BE.Event;
import BE.Event_Coordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EventsController extends BaseController{

    @FXML
    private BorderPane borderPaneEvent;

    @FXML
    private Button btnAssignCoordinator;

    @FXML
    private Button btnPrintTicket;

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblClass;

    @FXML
    private TextField lblEventCreator;

    @FXML
    private DatePicker lblEventDate;

    @FXML
    private TextField lblEventLocation;

    @FXML
    private Label lblEventName;

    @FXML
    private TextArea txaEventDescription;

    @FXML
    private TextField lblTicketLift;

    @FXML
    private TextField lblTicketSold;

    @FXML
    private ListView<?> lvAssignCoordinator;
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
        btnPrintTicket.setText("Delete Event");
    }

    private void setupEventCoordinator(){
        lblClass.setText("EventCoordinator");
        btnAssignCoordinator.setVisible(false);
        btnAssignCoordinator.setDisable(false);
    }

    private void setEventInfo() throws Exception {
        lblEventDate.setValue(LocalDate.parse(openedEvent.getEventDate().toString()));
        lblEventName.setText(openedEvent.getEventName());
        lblEventLocation.setText(openedEvent.getEventLocation());
        getUsersForCheck();
        lblTicketLift.getText();
        lblTicketSold.getText();

        //txaEventDescription.appendText(openedEvent.getEventDescription());

        String name = getModelsHandler().getAdminModel().getLocalUserFromId(openedEvent.getEventCreator()).getName();
        if (name != null)
            lblEventCreator.setText(name);
    }

    public void setOpenedEvent(Event openedEvent) {
        this.openedEvent = openedEvent;
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
