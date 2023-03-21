package GUI.Controllers;

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
    public ListView ListVUsers;

    @Override
    public void setup() {

        ListVUsers.setItems(getModelsHandler().getAdminModel().getUserObservableList());

        //.setCellValueFactory(new PropertyValueFactory<>("name"));

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
