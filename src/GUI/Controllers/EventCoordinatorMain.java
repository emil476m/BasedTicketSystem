package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EventCoordinatorMain extends BaseController{
    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextArea txaDescription;
    @FXML
    private TextField txtTicketAmount,txtSpecialTicketAmount;
    @FXML
    private ImageView imgEvent;
    @FXML
    private Label lblEventName;
    @FXML
    private Button btnLogOut;
    @FXML
    private BorderPane borderPaneEventCoordinator;

    public void handleCreateEvents(ActionEvent event) {

        String name = txtEventName.getText();
        String date = txtDate.getText();
        String location = txtLocation.getText();
        String Creator = txtEventName.getText();
        String Description = txaDescription.getText();
        int tickets = Integer.parseInt(txtTicketAmount.getText());
        int Specialtickets = Integer.parseInt(txtSpecialTicketAmount.getText());


    }

    public void handleOpen(ActionEvent event) {
        //checkSelectedItemType();
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
