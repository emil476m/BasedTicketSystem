package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserInfoController extends BaseController {
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

    private boolean createUser;

    @Override
    public void setup() {

    }

    public void setCreateUser(boolean createNewUser){
        this.createUser = createNewUser;
    }


    public void handleExit(ActionEvent actionEvent) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void handleDeleteUser(ActionEvent actionEvent) {
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
}
