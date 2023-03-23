package GUI.Controllers;

import BE.User;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdminController extends BaseController {


    @FXML
    private BorderPane borderPaneAdmin;
    @FXML
    private Button btnOpen;
    @FXML
    private VBox ScrollPaneEvent;
    @FXML
    private Button btnCreateUser;
    @FXML
    private ListView<User> listVUsers;
    @FXML
    private Button btnLogout;

    private String lastSelectedItemType;

    @Override
    public void setup() {
        try {
            getModelsHandler().getAdminModel().getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        listVUsers.setItems(getModelsHandler().getAdminModel().getUserObservableList());

        userListViewListener();
        dragScreen();

    }

    private void userListViewListener(){
        listVUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listVUsers.getSelectionModel().getSelectedItem() != null){

            }
        });
    }

    @FXML
    public void handleCreateUser(ActionEvent actionEvent) {
    }

    @FXML
    public void handleLogOut(ActionEvent actionEvent) {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleOpen(ActionEvent actionEvent) {
        checkSelectedItemType();
    }

    @FXML
    public void clickOnUser(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
        {
            checkSelectedItemType();
        }
    }

    private void checkSelectedItemType(){

        if (listVUsers.getSelectionModel().getSelectedItem() != null){
            openUserInfo();
        }
        /*else if ()*/
        //TODO implement event list check HERE!


    }

    private boolean shouldEditUser(){
        if (listVUsers.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        else
            return false;
    }

    private void openUserInfo(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/UserInfoView.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            ExceptionHandler.displayError(new Exception("Failed to open User Info", e));
        }

        Stage stage = new Stage();
        stage.setTitle("");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);

        UserInfoController controller = loader.getController();
        controller.setModel(getModelsHandler());

        //checks if user wants to create a new user or edit existing user.
        controller.setCreateUser(shouldEditUser());
        controller.setup();


        stage.showAndWait();
    }

    private void dragScreen(){
        borderPaneAdmin.setOnMousePressed(pressEvent -> {
            borderPaneAdmin.setOnMouseDragged(dragEvent -> {
                borderPaneAdmin.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneAdmin.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
}
