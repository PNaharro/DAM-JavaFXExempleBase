
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ControllerList {
    @FXML
    private Label text;

    public void setText(String text) {
      // Estableix el contingut del Label
      this.text.setText(text);
    }

    public Label getText() {
      return text;
    }
}
