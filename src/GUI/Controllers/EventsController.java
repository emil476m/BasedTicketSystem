package GUI.Controllers;

import BE.Event;
import BE.Event_Coordinator;
import BE.User;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;

public class EventsController extends BaseController{

    public Button btnRemoveCoordinator;
    @FXML
    private BorderPane borderPaneEvent;

    @FXML
    private Button btnAssignCoordinator, btnEditEvent, btnSellTicket, btnReturn;

    @FXML
    private Label lblClass;

    @FXML
    private DatePicker dpEventDate;

    @FXML
    private Label lblEventName;

    @FXML
    private TextArea txaEventDescription;

    @FXML
    private TextField txfTicketsLeft, txfEventLocation, txfEventCreator, txfSTicketsLeft;

    @FXML
    private ListView<User> lvAssignCoordinator;
    private Event openedEvent;

    private Event_Coordinator creator;

    @Override
    public void setup() {
        try {
            checkUserAndSetup();
            setEventInfo();
            disableElements();
            restricAccess();
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed on setup", e));
            e.printStackTrace();
        }
    }

    private void disableElements()
    {
        txfTicketsLeft.setDisable(true);
        txaEventDescription.setDisable(true);
        txfEventCreator.setDisable(true);
        txfEventLocation.setDisable(true);
        dpEventDate.setDisable(true);
        txfSTicketsLeft.setDisable(true);
    }

    private void checkUserAndSetup() throws Exception {
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
        btnSellTicket.setText("Delete Event");
    }

    private void setupEventCoordinator() throws Exception {
        if (getModelsHandler().getAdminModel().getUserObservableList().isEmpty() || getModelsHandler().getAdminModel().getUserObservableList() == null)
            getModelsHandler().getAdminModel().retreiveAllUsers();
        lblClass.setText("EventCoordinator");
        btnAssignCoordinator.setVisible(false);
        btnRemoveCoordinator.setVisible(false);
        btnAssignCoordinator.setDisable(true);
        btnRemoveCoordinator.setDisable(true);
    }

    private void restricAccess() {
        if (getModelsHandler().getLoginModel().getLoggedinECoordinator() != null) {
            boolean userHasAccess = false;
            int loggedInUserId = getModelsHandler().getLoginModel().getLoggedinECoordinator().getUserID();
            for (User u : getModelsHandler().getAdminModel().getCurrentEventEventCoordinators()) {
                if (u.getUserID() == loggedInUserId) {
                    userHasAccess = true;
                }
            }
            if (!userHasAccess){
                btnEditEvent.setDisable(true);
                btnSellTicket.setDisable(true);
            }
        }
    }

    private void setEventInfo() throws Exception {
        getModelsHandler().getAdminModel().getCurrentEventEventCoordinators().clear();
        getModelsHandler().getAdminModel().getCurrentEventEventCoordinators().addAll(getModelsHandler().getAdminModel().getUsersWorkingOnEvent(openedEvent));
        lvAssignCoordinator.setItems(getModelsHandler().getAdminModel().getCurrentEventEventCoordinators());

        dpEventDate.setValue(LocalDate.parse(openedEvent.getEventDate().toString()));
        lblEventName.setText(openedEvent.getEventName());
        txfEventLocation.setText(openedEvent.getEventLocation());
        getUsersForCheck();
        String nt = ""+openedEvent.getTickets();
        String st = ""+ openedEvent.getSpecialTickets();
        txfTicketsLeft.setText(nt);
        txfSTicketsLeft.setText(st);
        txaEventDescription.appendText(openedEvent.getEventDescription());

        if (openedEvent.getEventCreator() != 1){
            String name = getModelsHandler().getAdminModel().getLocalUserFromId(openedEvent.getEventCreator()).getName();
            creator = (Event_Coordinator) getModelsHandler().getAdminModel().getLocalUserFromId(openedEvent.getEventCreator());
            if (name != null)
                txfEventCreator.setText(name);
        }
        else if (openedEvent.getEventCreator() == 1){
            txfEventCreator.setText("NotAssigned");
            creator = (Event_Coordinator) getModelsHandler().getAdminModel().getLocalUserFromId(openedEvent.getEventCreator());

        }

    }

    public void setOpenedEvent(Event openedEvent) {
        this.openedEvent = openedEvent;
    }


    public void handleSellTicket(ActionEvent event) {
        try {
            if (btnSellTicket.getText().equals("Delete Event")) {
                deleteEvent();
            } else if (btnSellTicket.getText().equals("Sell ticket")) {
                sellTicket();
            }
        } catch (Exception e) {
            e.printStackTrace();
          ExceptionHandler.displayError(new Exception("Failed to do action pleas try again", e));
        }
    }

    private void sellTicket() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/TicketView.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Failed to open Event Info", e));
        }

        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/GUI/Images/EA.png"));

        TicketViewController controller = loader.getController();
        controller.setModel(getModelsHandler());

        controller.setEvent(openedEvent);
        controller.setup();

        stage.showAndWait();
    }

    private void deleteEvent() throws Exception {
        getModelsHandler().getEventCoordinatorModel().removeEventFromLocal(openedEvent);
        getModelsHandler().getAdminModel().deleteEvent(openedEvent);
        handleReturn();
    }

    public void handleAssignCoordinator(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/EventCoordinatorListView.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Failed to open Event Coordinator List", e));
        }

        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/GUI/Images/EA.png"));

        EventCoordinatorListController controller = loader.getController();
        controller.setModel(getModelsHandler());
        controller.setOpenedEvent(openedEvent);

        controller.setup();

        stage.showAndWait();
    }

    public void handleReturn() {
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

    public void handleEditEvent(ActionEvent actionEvent) {
        try {
            if (btnEditEvent.getText().equals("Edit")) {
                txfTicketsLeft.setDisable(false);
                txaEventDescription.setDisable(false);
                txfEventLocation.setDisable(false);
                dpEventDate.setDisable(false);
                txfSTicketsLeft.setDisable(false);
                btnEditEvent.setText("Confirm");
            } else if (btnEditEvent.getText().equals("Confirm")) {
                LocalDate date = dpEventDate.getValue();
                Event event;
                if (creator != null){
                    event = new Event(openedEvent.getId() , lblEventName.getText(), date, txfEventLocation.getText(), creator.getUserID(), txaEventDescription.getText(), Integer.parseInt(txfTicketsLeft.getText()), Integer.parseInt(txfSTicketsLeft.getText()));
                }
                else
                    event = new Event(openedEvent.getId() , lblEventName.getText(), date, txfEventLocation.getText(), 1, txaEventDescription.getText(), Integer.parseInt(txfTicketsLeft.getText()), Integer.parseInt(txfSTicketsLeft.getText()));
                
                getModelsHandler().getEventCoordinatorModel().updateEvent(event, openedEvent);
                disableElements();
                btnEditEvent.setText("Edit");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ExceptionHandler.displayError(new Exception("Failed to update event please try again", e));
        }
    }

    public void handleRemoveCoordinator(ActionEvent actionEvent) {
        if (lvAssignCoordinator.getSelectionModel().getSelectedItem() != null){
            try {
                getModelsHandler().getAdminModel().removeUserFromEvent(lvAssignCoordinator.getSelectionModel().getSelectedItem(), openedEvent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
