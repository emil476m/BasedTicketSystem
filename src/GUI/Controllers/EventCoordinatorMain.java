package GUI.Controllers;

import BE.Event;
import BE.Event_Coordinator;
import GUI.Models.ModelsHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class EventCoordinatorMain extends BaseController{
    @FXML
    private GridPane menuGridPane;
    @FXML
    private TextField txtEventName;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextArea txaDescription;
    @FXML
    private TextField txtTicketAmount, txtSpecialTicketAmount, txtLocation;
    @FXML
    private ImageView imgEvent;
    @FXML
    private Button btnLogOut;
    @FXML
    private BorderPane borderPaneEventCoordinator;
    private ObservableList<Event> eventObservableList;

    private Alert alert;

    public void handleCreateEvents(ActionEvent event) {

        if (checkTextFieldsNotNull()) {
            String name = txtEventName.getText();
            LocalDate date = txtDate.getValue();
            String location = txtLocation.getText();
            int creator = getModelsHandler().getLoginModel().getLoggedinECoordinator().getUserID();
            String description = txaDescription.getText();
            int tickets = Integer.parseInt(txtTicketAmount.getText());
            int specialTickets = Integer.parseInt(txtSpecialTicketAmount.getText());

            Event newEvent = null;
            Event_Coordinator newEC = null;

            try {
                    newEvent = new Event(name, date, location, creator, description, tickets, specialTickets);
                    newEC = getModelsHandler().getLoginModel().getLoggedinECoordinator();

                getModelsHandler().getEventCoordinatorModel().createEvent(newEvent, newEC);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }



    private boolean checkTextFieldsNotNull(){
        if (checktxtEventName() && checktxtDate() && checktxtLocation()){
            if (checktxaDescription() && checktxtTicketAmount() && checktxtSpecialTicketAmount())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private boolean checktxtEventName() {
        if (!txtEventName.getText().isEmpty() && txtEventName.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxtDate() {
        if (!txtDate.getValue().equals("") && txtDate.getValue() != null) return true;
        else
            return false;
    }
    private boolean checktxtLocation() {
        if (!txtLocation.getText().isEmpty() && txtLocation.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxaDescription() {
        if (!txaDescription.getText().isEmpty() && txaDescription.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxtTicketAmount() {
        if (!txtTicketAmount.getText().isEmpty() && txtTicketAmount.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxtSpecialTicketAmount() {
        if (!txtSpecialTicketAmount.getText().isEmpty() && txtSpecialTicketAmount.getText() != null) return true;
        else
            return false;
    }

    public void eventDisplayCard(){
        for (Event e : eventObservableList) {
            menuGridPane.getChildren().addAll(createEventAnchorPane(e));
        }

    }
    private AnchorPane createEventAnchorPane(Event event)
    {
        AnchorPane ev = new AnchorPane();
        ev.getChildren().add(new ImageView());
        ev.getChildren().add(new Label(event.getEventName()));
        return ev;
    }

    public void handleOpen(ActionEvent event) {
        //checkSelectedItemType();
    }

    public void handleLogOut(ActionEvent event) {
        try{

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setContentText("Are you sure want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                // Link your login form and show it
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/LoginView.fxml"));
                Parent root = loader.load();

                Stage stage1 = new Stage();
                Scene scene = new Scene(root);

                BaseController controller = loader.getController();
                controller.setModel(new ModelsHandler());
                controller.setup();

                stage1.setTitle("EventMaster");
                stage1.initStyle(StageStyle.UNDECORATED);
                stage1.getIcons().add(new Image("/GUI/Images/EA.png"));
                stage1.setScene(scene);
                stage1.show();

                Stage stage = (Stage) btnLogOut.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {e.printStackTrace();}

    }
    @FXML
    private void dragScreen(){
        borderPaneEventCoordinator.setOnMousePressed(pressEvent -> {
            borderPaneEventCoordinator.setOnMouseDragged(dragEvent -> {
                borderPaneEventCoordinator.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneEventCoordinator.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    @Override
    public void setup() {
        try {
            getModelsHandler().getEventCoordinatorModel().getAllEvents();
            eventObservableList = getModelsHandler().getEventCoordinatorModel().getEventObservableList();
            eventDisplayCard();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        dragScreen();
    }
}
