package GUI.Controllers;

import BE.Event;
import BE.User;
import GUI.Models.ModelsHandler;
import GUI.Util.ExceptionHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class AdminController extends BaseController {


    @FXML
    private GridPane menuGridPane;
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
    private ObservableList<Event> eventObservableList;

    @Override
    public void setup() {
        try {
            getModelsHandler().getAdminModel().getAllUsers();
            getModelsHandler().getEventCoordinatorModel().getAllEvents();
            eventObservableList = getModelsHandler().getEventCoordinatorModel().getEventObservableList();
            eventDisplayCard();
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

    private User getSelectedUser(){
        User user = listVUsers.getSelectionModel().getSelectedItem();
        if (user != null){
            return user;
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

    public void handleGridClick(MouseEvent mouseEvent) {
        /*AnchorPane ev = null;
        Node node = (Node) mouseEvent.getSource();
        Parent p = node.getParent();
        if (menuGridPane.getChildren().contains(p))
            System.out.println("node exist");
        System.out.println("test");
        System.out.println("node print: " + node);*/

        /*while (p != menuGridPane) {
            //node = p;
            //p = p.getParent();
            System.out.println("f");
        }*/
        //System.out.println("node print: " + node.getClass());

        //ev = (AnchorPane) node;

    }

    /*private void testClickNodes(){
        menuGridPane.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            while (node != null && node != menuGridPane){
                node = node.getParent();
            }

            if (node instanceof AnchorPane){
                evt.consume();

                AnchorPane ev = (AnchorPane) node;

                System.out.println("node to anchorPane " + ev);
            }



        });
    }*/
}