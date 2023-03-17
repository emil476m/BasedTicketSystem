package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginViewController extends BaseController {

    @FXML
    private Button BtnClose;
    @Override
    public void setup() {
    }


    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) BtnClose.getScene().getWindow();
        stage.close();
    }
}
