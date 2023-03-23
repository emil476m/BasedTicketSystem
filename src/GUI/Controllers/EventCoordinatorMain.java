package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EventCoordinatorMain extends BaseController{
    public TextField txtEventName;
    public TextField txtDate;
    public TextField txtLocation;
    public TextArea txaDescription;
    public TextField txtTicketAmount;
    public Button btnImageChooseFile;
    public ImageView imgEvent;
    public Label lblEventName;
    public Button btnLogOut;
    public BorderPane borderPaneEventCoordinator;

    public void handleCreateEvents(ActionEvent event) {
    }

    public void handleEditEvents(ActionEvent event) {
    }

    public void handleOpen(ActionEvent event) {
    }

    public void handleImageChooseFile(ActionEvent event) {
    }

    public void handleLogOut(ActionEvent event) {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

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

    }
}
