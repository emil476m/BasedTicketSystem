package GUI.Controllers;

import BE.Event;
import BE.Event_Coordinator;
import BE.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EventCoordinatorListController extends BaseController{
    @FXML
    private TableView<User> tbvUserList;
    @FXML
    private TableColumn<User, Integer> tbcUserListId;
    @FXML
    private TableColumn<User, String> tbcUserListName;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnConfirm;
    @FXML
    private BorderPane borderPaneEventCoordinatorList;

    private Event openedEvent;

    private ObservableList<User> allEventCoordinators;

    public void setOpenedEvent(Event openedEvent) {
        this.openedEvent = openedEvent;
    }

    @Override
    public void setup() {
        getAndShowEventCoordinators();
        multiSelect();
    }

    public EventCoordinatorListController(){
        allEventCoordinators = FXCollections.observableArrayList();
    }

    private void getAndShowEventCoordinators(){
        allEventCoordinators.addAll(getModelsHandler().getAdminModel().getCurrentEventEventCoordinators());

        for (User u:getModelsHandler().getAdminModel().getAllUsers()){
            if (u.getClass().getSimpleName() == Event_Coordinator.class.getSimpleName()){
                if (!allEventCoordinators.contains(u))
                    allEventCoordinators.add(u);
                else
                    allEventCoordinators.remove(u);
            }
        }
        tbvUserList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbvUserList.setItems(allEventCoordinators);
        tbcUserListName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbcUserListId.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

    public void dragScreen() {
        borderPaneEventCoordinatorList.setOnMousePressed(pressEvent -> {
            borderPaneEventCoordinatorList.setOnMouseDragged(dragEvent -> {
                borderPaneEventCoordinatorList.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneEventCoordinatorList.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void handleExit() {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    public void handleConfirm(ActionEvent actionEvent) {
        try {
            for (User u : tbvUserList.getSelectionModel().getSelectedItems()) {

                getModelsHandler().getAdminModel().assignEventToUser(u, openedEvent);

            }
            handleExit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Makes the tableview able to multiselect without holding control,
     * by using node instances and mouse events.
     */
    private void multiSelect(){
        tbvUserList.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
            Node node = evt.getPickResult().getIntersectedNode();

            // Gets the row that was clicked.
            while (node != null && node != tbvUserList && !(node instanceof TableRow)) {
                node = node.getParent();
            }

            // Don't use standard event, if node is a table row.
            if (node instanceof TableRow) {
                // Prevent further handling
                evt.consume();

                TableRow row = (TableRow) node;
                TableView tv = row.getTableView();

                // Focus the tableview
                tv.requestFocus();

                if (!row.isEmpty()) {
                    // Handle selection for non-empty nodes
                    int index = row.getIndex();

                    if (row.isSelected()) {
                        tv.getSelectionModel().clearSelection(index);
                    } else {
                        tv.getSelectionModel().select(index);
                    }
                }
            }
        });
    }
}
