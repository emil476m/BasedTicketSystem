package GUI.Controllers;

import BE.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventsCardController extends BaseController{
    @FXML
    private ImageView EventCardImage;
    @FXML
    private Label lblName;

    private Event event;
    private Image image;

    public void setEvent(Event event){
        this.event = event;

        lblName.setText(event.getEventName());
        String path = "File:" + event.getImage();
        image = new Image(event.getImage(), 400, 150, false, true);
        EventCardImage.setImage(image);
    }

    public void imgToEvent(MouseEvent mouseEvent) {
    }

    @Override
    public void setup() {

    }
}
