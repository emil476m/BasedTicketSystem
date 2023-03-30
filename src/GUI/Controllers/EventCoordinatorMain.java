package GUI.Controllers;

import BE.Event;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Optional;

public class EventCoordinatorMain extends BaseController{
    public GridPane menuGridPane;
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
    private ObservableList<Event> eventObservableList;


    private Alert alert;

    public void handleCreateEvents(ActionEvent event) {

        String name = txtEventName.getText();
        String date = txtDate.getText();
        String location = txtLocation.getText();
        String Creator = txtEventName.getText();
        String Description = txaDescription.getText();
        int tickets = Integer.parseInt(txtTicketAmount.getText());
        int Specialtickets = Integer.parseInt(txtSpecialTicketAmount.getText());


    }

    public void EventDisplayCard(){
        eventObservableList.clear();
        eventObservableList.addAll();

        int row = 0;
        int column = 0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for (int i = 0; i < eventObservableList.size(); i++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("src/GUI/Views/EventsCard.fxml"));
                AnchorPane pane = load.load();
                EventsCardController ecc = load.getController();
                ecc.setEvent(eventObservableList.get(i));

                if (column == 1) {
                    column = 0;
                    row += 1;
                }

                menuGridPane.add(pane, column, row++);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                Parent root = FXMLLoader.load(getClass().getResource("/GUI/Views/LoginView.fxml"));

                Stage stage1 = new Stage();
                Scene scene = new Scene(root);

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
            getModelsHandler().getEventCoordinatorModel().getEventObservableList();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        //EventDisplayCard();
        dragScreen();
    }
}
