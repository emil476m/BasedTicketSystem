package GUI.Controllers;

import BE.Admin;
import BE.Event;
import BE.Event_Coordinator;
import BE.User;
import GUI.Util.AlertOpener;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInfoController extends BaseController {
    @FXML
    private MenuItem menuItemAdmin;
    @FXML
    private MenuItem menuItemEventCoordinator;
    @FXML
    private Text txtUserId;
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
        setEditAbleUser(false);
        if (!checkShouldEdit()){
            btnDeleteUser.setDisable(true);
            btnDeleteUser.setVisible(false);
            btnEditUser.setDisable(true);
            btnEditUser.setVisible(false);
            txtUserId.setVisible(false);
            txtfUserId.setVisible(false);
            setEditAbleUser(true);
        }
        else if (checkShouldEdit()) {
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
            txtfUserId.setEditable(false);

            mAccessLevel.setDisable(false);
            txtfUsername.setEditable(true);
            txtfPassword.setEditable(true);
            txtfName.setEditable(true);
            txtfMail.setEditable(true);

        } else if (!ableToEdit) {
            mAccessLevel.setDisable(true);
            txtfAcceslevel.setEditable(false);
            txtfUserId.setEditable(false);
            txtfUsername.setEditable(false);
            txtfPassword.setEditable(false);
            txtfName.setEditable(false);
            txtfMail.setEditable(false);
        }

    }

    public void handleDeleteUser(ActionEvent actionEvent) {
        if (selectedUser != null){
            try {
                for (Event e: getModelsHandler().getEventCoordinatorModel().getEventObservableList()){
                    if (selectedUser.getUserID() == e.getEventCreator()){
                        e.setEventCreator(1);
                        getModelsHandler().getEventCoordinatorModel().updateEvent(e, e);
                    }
                }
                getModelsHandler().getAdminModel().deleteUser(selectedUser);
            } catch (Exception e) {
                e.printStackTrace();
                ExceptionHandler.displayError(new Exception("Failed to delete user",e));
            }
        }
        handleExit();
    }

    public void handleEditUser(ActionEvent actionEvent) {
        setEditAbleUser(true);
    }

    public void handleConfirm(ActionEvent actionEvent) {
        if (checkTextFieldsNotNull()) {
            if (!checkShouldEdit()) {
                createUser();
            } else if (checkShouldEdit()) {
                editUser();
            }
        }
        else
            AlertOpener.validationError("Missing Input!");
    }

    private void createUser(){

        String passWord = txtfPassword.getText();
        String userName = txtfUsername.getText();
        String mail = txtfMail.getText();
        String name = txtfName.getText();

        User newUser = null;

        try {
            if (getModelsHandler().getAdminModel().checkUserName(userName)) {
                System.out.println("username is not the same");
                if (txtfAcceslevel.getText().equals(Admin.class.getSimpleName())) {
                    newUser = new Admin(passWord, userName, mail, name);
                } else if (txtfAcceslevel.getText().equals(Event_Coordinator.class.getSimpleName())) {
                    newUser = new Event_Coordinator(passWord, userName, mail, name);
                }
                if (newUser != null) {
                    getModelsHandler().getAdminModel().createUser(newUser);
                    handleExit();
                }
            }
            else {
                AlertOpener.validationError("Username is already taken!");
            }
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to create user please try again",e));
        }
    }

    private void editUser(){
        String passWord = txtfPassword.getText();
        String userName = txtfUsername.getText();
        String mail = txtfMail.getText();
        String name = txtfName.getText();
        int userId = Integer.parseInt(txtfUserId.getText());

        User user = null;

        try {
            if (txtfAcceslevel.getText().equals(Admin.class.getSimpleName())) {
                user = new Admin(userId, passWord, userName, mail, name);

            } else if (txtfAcceslevel.getText().equals(Event_Coordinator.class.getSimpleName())) {
                user = new Event_Coordinator(userId, passWord, userName, mail, name);
            }
            if (checkIfTwoUserAreSame(user)) {
                handleExit();
            }
            else {
                if (checkUsernameIsSame(user)){
                    if (user.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName())) {
                        getModelsHandler().getAdminModel().updateUser(user, selectedUser);
                        handleExit();
                    }else {
                        getModelsHandler().getAdminModel().deleteUser(selectedUser);
                        getModelsHandler().getAdminModel().createUser(user);
                        handleExit();
                    }
                } else if (getModelsHandler().getAdminModel().checkUserName(user.getUserName())) {
                    if (user.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName())) {
                        getModelsHandler().getAdminModel().updateUser(user, selectedUser);
                        handleExit();
                    }
                    else {
                        getModelsHandler().getAdminModel().deleteUser(selectedUser);
                        getModelsHandler().getAdminModel().createUser(user);
                        handleExit();
                    }
                }
                else
                    AlertOpener.validationError("username is already taken!");
            }
        } catch (Exception e) {
            ExceptionHandler.displayError(new Exception("Failed to edit user please try again"));
        }
    }

    /**
     * returns true if the two users are the same.
     * @param user1
     * @return
     */
    private boolean checkIfTwoUserAreSame(User user1){
        if (user1.getUserID() == selectedUser.getUserID()){
            if (user1.getClass().getSimpleName().equals(selectedUser.getClass().getSimpleName()))
                if (user1.getName().equals(selectedUser.getName()))
                    if (user1.getUserName().equals(selectedUser.getUserName()))
                        if (user1.getPassWord().equals(selectedUser.getPassWord()))
                            if (user1.getMail().equals(selectedUser.getMail()))
                                return true;
        }
        return false;
    }

    /**
     * Returns true if the 2 usernames are the same else returns false
     * @param
     * @return
     */
   private boolean checkUsernameIsSame(User user1){
        if (user1.getUserName().equals(selectedUser.getUserName())){
            return true;
        }
        else
            return false;
    }

    private boolean checkTextFieldsNotNull(){
        if (checktxtfName() && checktxtfUserName() && checktxtfPassword()){
            if (checktxtfMail() && checktxtfAccessLevel())
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private boolean checktxtfName(){
        if (!txtfName.getText().isEmpty() && txtfName.getText() != null) return true;
        else
            return false;
    }
    private boolean checktxtfUserName(){
        if (!txtfUsername.getText().isEmpty() && txtfUsername.getText() != null)
            return true;
        else
            return false;
    }
    private boolean checktxtfPassword(){
        if (!txtfPassword.getText().isEmpty() && txtfPassword.getText() != null)
            return true;
        else
            return false;
    }
    private boolean checktxtfMail(){
        if (!txtfMail.getText().isEmpty() && txtfMail.getText() != null)
            return true;
        else
            return false;
    }
    private boolean checktxtfAccessLevel(){
        if (!txtfAcceslevel.getText().isEmpty() && txtfAcceslevel.getText() != null)
            return true;
        else
            return false;
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
