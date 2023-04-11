package GUI.Controllers;

import BE.Event;
import BE.User;
import GUI.Models.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class AdminController extends BaseController {


    @FXML
    private ListView<Event> listVEvents;
    @FXML
    private TextField txtfSearch;
    @FXML
    private BorderPane borderPaneAdmin;
    @FXML
    private Button btnOpen;
    @FXML
    private Button btnCreateUser;
    @FXML
    private ListView<User> listVUsers;
    @FXML
    private Button btnLogout;

    private String lastSelectedItemType;
    private Alert alert;

    @Override
    public void setup() {
        try {
            getModelsHandler().getAdminModel().retreiveAllUsers();
            getModelsHandler().getEventCoordinatorModel().getAllEvents();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        listVUsers.setItems(getModelsHandler().getAdminModel().getUserObservableList());
        listVEvents.setItems(getModelsHandler().getEventCoordinatorModel().getEventObservableList());

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
        openUserInfo();
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

                Stage stage = (Stage) btnLogout.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {e.printStackTrace();}

    }

    @FXML
    public void handleOpen(ActionEvent actionEvent) {
        checkSelectedItemType();
    }

    @FXML
    public void clickOnUser(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
        {
            lastSelectedItemType = "User";
            checkSelectedItemType();
        }
    }

    private void checkSelectedItemType(){

        if (listVUsers.getSelectionModel().getSelectedItem() != null&& lastSelectedItemType.equals("User")){
            openUserInfo();
        }
        else if (listVEvents.getSelectionModel().getSelectedItem() != null && lastSelectedItemType.equals("Event"))
            openEvent();
    }

    private User getSelectedUser(){
        User user = listVUsers.getSelectionModel().getSelectedItem();
        if (user != null){
            return user;
        }
        else
            return null;
    }

    private Event getSelectedEvent(){
        Event event = listVEvents.getSelectionModel().getSelectedItem();
        if (event != null){
            return event;
        }
        else
            return null;
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
        stage.getIcons().add(new Image("/GUI/Images/EA.png"));

        UserInfoController controller = loader.getController();
        controller.setModel(getModelsHandler());

        //checks if user wants to create a new user or edit existing user.
        controller.setCreateUser(getSelectedUser());
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

    private void search() {
        String search = txtfSearch.getText().toLowerCase();

        if(search != null)
            getModelsHandler().getAdminModel().searchUsers(search);
        else if (search == null){
            getModelsHandler().getAdminModel().clearSearch();
        }
    }

    @FXML
    private void searchOnButtonPress(KeyEvent keyEvent) {
        search();
    }


    public void clickOnEvent(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.PRIMARY && mouseEvent.getClickCount() == 2)
        {
            lastSelectedItemType = "Event";
            checkSelectedItemType();
        }
    }

    private void openEvent(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/EventsInfoView.fxml"));
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

        EventsController controller = loader.getController();
        controller.setModel(getModelsHandler());

        controller.setOpenedEvent(getSelectedEvent());
        controller.setup();

        stage.showAndWait();
    }
}