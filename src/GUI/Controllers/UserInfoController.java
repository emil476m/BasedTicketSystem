package GUI.Controllers;

import BE.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserInfoController extends BaseController {
    public MenuItem menuItemAdmin;
    public MenuItem menuItemEventCoordinator;
    @FXML
    private Menu mAccessLevel;
    @FXML
    private BorderPane borderPaneUserInfo;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnDeleteUser;
    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnConfirm;
    @FXML
    private TextField txtfUsername;
    @FXML
    private TextField txtfPassword;
    @FXML
    private TextField txtfMail;
    @FXML
    private TextField txtfName;
    @FXML
    private TextField txtfUserId;
    @FXML
    private TextField txtfAcceslevel;

    private User selectedUser;

    @Override
    public void setup() {
        setUpUserInfo();

    }

    public void setCreateUser(User user){
        this.selectedUser = user;
    }


    public void handleExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    private boolean checkShouldEdit(){
        if (selectedUser != null)
            return true;
            else
                return false;
    }

    private void setUpUserInfo(){
        if (!checkShouldEdit()){
            System.out.println("test");

            btnDeleteUser.setDisable(true);
            btnDeleteUser.setVisible(false);
            btnEditUser.setDisable(true);
            btnEditUser.setVisible(false);
        }
        else if (checkShouldEdit()) {
            setEditAbleUser(true);
            setUserInfoInTxtf();
        }

    }

    private void setUserInfoInTxtf(){
        txtfMail.setText(selectedUser.getMail());
        txtfName.setText(selectedUser.getName());
        txtfPassword.setText(selectedUser.getPassWord());
        txtfUserId.setText(selectedUser.getUserID()+"");
        txtfUsername.setText(selectedUser.getUserName());
        txtfAcceslevel.setText(selectedUser.getClass().getSimpleName());
    }

    private void setEditAbleUser(boolean ableToEdit){
        if (ableToEdit){
            txtfAcceslevel.setEditable(false);

        } else if (!ableToEdit) {
            mAccessLevel.setDisable(true);
            txtfAcceslevel.setEditable(false);
        }

    }

    public void handleDeleteUser(ActionEvent actionEvent) {
        if (selectedUser != null){
            try {
                getModelsHandler().getAdminModel().deleteUser(selectedUser);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        handleExit();
    }

    public void handleEditUser(ActionEvent actionEvent) {
    }

    public void handleConfirm(ActionEvent actionEvent) {
    }

    @FXML
    private void dragScreen(MouseEvent mouseEvent){
        borderPaneUserInfo.setOnMousePressed(pressEvent -> {
            borderPaneUserInfo.setOnMouseDragged(dragEvent -> {
                borderPaneUserInfo.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneUserInfo.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void handleMenuItemAdmin(ActionEvent actionEvent) {
        txtfAcceslevel.setText(menuItemAdmin.getText());
    }

    public void handleMenuItemEventCoordinator(ActionEvent actionEvent) {
        txtfAcceslevel.setText(menuItemEventCoordinator.getText());
    }
}
