package GUI.Controllers;

import BE.Event_Coordinator;
import GUI.Models.ModelsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

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

    private Alert alert;

    @Override
    public void setup() {

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
