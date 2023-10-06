import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ControllerMobile0 implements Initializable{


@FXML
private VBox yPanel;    

String opcions[] = { "Personatges", "Jocs", "Consoles" };

{
    
}
@Override
public void initialize(URL location, ResourceBundle resources) {
    try {
        showopcs();
      } catch (Exception e) {
        System.out.println("ControllerDesktop: Error showing list.");
      }
}

public void showopcs() throws Exception{
    URL resource = this.getClass().getResource("assets/listatem.fxml");
    yPanel.getChildren().clear();

    for (int i = 0; i < 3; i++) {
       
        String nom = opcions[i];
        System.out.println(nom);
        FXMLLoader loader = new FXMLLoader(resource);
         System.out.println("a2");
        Parent itemTemplate = loader.load();
        System.out.println("a");
        ControllerList itemController = loader.getController();
        System.out.println("a3");
        itemController.setText(nom);
        System.out.println("a5");
        yPanel.getChildren().add(itemTemplate);
        System.out.println("a4");
    }
}
}


