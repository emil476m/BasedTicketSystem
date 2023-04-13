package GUI.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TicketViewController extends BaseController{
    @FXML
    private BorderPane borderPaneTicket;
    @FXML
    private Button btnSendTicket, btnPrintTicket, btnReturn;
    @FXML
    private TextField txtfTicketType;
    @FXML
    private MenuItem menuItemBeerTicketType,menuItemVIPTicketType,menuItemNormalTickettype;

    @Override
    public void setup() {
        btnSendTicket.setDisable(true);
        btnPrintTicket.setDisable(true);
        txtfTicketType.setEditable(false);
    }

    @FXML
    private void dragScreen(){
        borderPaneTicket.setOnMousePressed(pressEvent -> {
            borderPaneTicket.setOnMouseDragged(dragEvent -> {
                borderPaneTicket.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPaneTicket.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

    public void handleSendTicket(ActionEvent actionEvent) {

    }

    public void handlePrintTicket(ActionEvent actionEvent) {
    }

    public void handleBeerTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemBeerTicketType.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleVIPTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemVIPTicketType.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleNormalTicketType(ActionEvent actionEvent) {
        txtfTicketType.setEditable(true);
        txtfTicketType.setText(menuItemNormalTickettype.getText());
        txtfTicketType.setEditable(false);
    }

    public void handleReturn(ActionEvent event) {
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }
}
