package GUI.Controllers;

import BE.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class AdminController extends BaseController {


    public VBox ScrollPaneEvent;
    public Button btnCreateUser;
    public Button btnEditUser;
    public Button btnDeleteUser;
    public Button btnDeleteEvent;
    public ListView<User> ListVUsers;

    @Override
    public void setup() {
        try {
            getModelsHandler().getAdminModel().getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ListVUsers.setItems(getModelsHandler().getAdminModel().getUserObservableList());


    }

    public void handleCreateUser(ActionEvent actionEvent) {
    }

    public void handleEditUser(ActionEvent actionEvent) {
    }

    public void handleDeleteUser(ActionEvent actionEvent) {

    }

    public void handleDeleteEvent(ActionEvent actionEvent) {
    }
}
