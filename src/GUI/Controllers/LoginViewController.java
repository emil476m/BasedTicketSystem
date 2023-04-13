package GUI.Controllers;

import BE.Admin;
import BE.Event_Coordinator;
import GUI.Util.ExceptionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginViewController extends BaseController {
    @FXML
    private TextField TxtUsername;
    @FXML
    private TextField TxtPassWord;
    @FXML
    private Button BtnClose, BtnLogin;

    private ExceptionHandler exceptionHandler;
    @Override
    public void setup() {
        exceptionHandler = new ExceptionHandler();
    }


    public void handleClose(ActionEvent event) {
        Stage stage = (Stage) BtnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * handles the login action
     * @param event
     */
    public void handleLogin(ActionEvent event) {
        try {
            String username = TxtUsername.getText();
            String password = TxtPassWord.getText();
            getModelsHandler().getLoginModel().loginAction(username, password);
            Admin admin = getModelsHandler().getLoginModel().getLoggedInAdmin();
            Event_Coordinator ec = getModelsHandler().getLoginModel().getLoggedinECoordinator();
            if (admin != null) {
                openAdminView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            } else if (ec != null) {
                openEventCoordinatorView();
                Stage stage = (Stage) BtnLogin.getScene().getWindow();
                stage.close();
            }
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Incorrect username or password, please try again");
            alert.showAndWait();
        }
    }

    /**
     * opens the AdminView when called
     * @throws IOException
     */
    private void openAdminView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/AdminView.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        BaseController controller = loader.getController();
        controller.setModel(super.getModelsHandler());
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("Event Master (Admin)");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/GUI/Images/EA.png"));
        stage.show();
    }

    /**
     * opens the EventCoordinatorView when called
     * @throws IOException
     */
    private void openEventCoordinatorView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Views/EventCoordinatorView.fxml"));

        Parent root = loader.load();
        Stage stage = new Stage();

        BaseController controller = loader.getController();
        controller.setModel(super.getModelsHandler());
        controller.setup();

        stage.setScene(new Scene(root));
        stage.setTitle("Event Master (Coordinator)");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image("/GUI/Images/EA.png"));
        stage.show();
    }
}
