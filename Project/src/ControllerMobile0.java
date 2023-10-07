

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.View;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;



public class ControllerMobile0  implements Initializable{

    @FXML
    private VBox yPanel;    
    
    String opcions[] = { "Personatges", "Jocs", "Consoles" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
      load();
    } 



    public void load(){
        try {
            showList();
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list.");
          }
    }
    public void showList() throws Exception {

      
        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
    
       
        // Carregar la plantilla
        URL resource = this.getClass().getResource("assets/listatem.fxml");
    
        // Esborrar la llista actual
        yPanel.getChildren().clear();
    
        // Carregar la llista amb les dades
        for (int i = 0; i < 3; i++) {
                String nom = opcions[i];
                FXMLLoader loader = new FXMLLoader(resource);
                Parent itemTemplate = loader.load();
                ControllerList itemController = loader.getController();
                itemController.setText(nom);
                final String type =  opcions[i];
                final int index = i;
                itemTemplate.setOnMouseClicked(event -> {
                  UtilsViews.setView("Movile1");
                });
                
                
                
                yPanel.getChildren().add(itemTemplate);
             
        }
    }
  void showInfo(String type, int index) {

        // Obtenir una referència a l'ojecte AppData que gestiona les dades
        AppData appData = AppData.getInstance();
      
        // Obtenir les dades de l'opció seleccionada
        JSONObject dades = appData.getItemData(type, index);
      
        // Carregar la plantilla
        URL resource = this.getClass().getResource("assets/template_info_item.fxml");
      
        // Esborrar la informació actual
        //info.getChildren().clear();
        // Carregar la llista amb les dades
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerInfoItem itemController = loader.getController();
            itemController.setImage("assets/images/" + dades.getString("imatge"));
            itemController.setTitle(dades.getString("nom"));
            switch (type) {
            case "Consoles": itemController.setText(dades.getString("procesador")+"\n"+dades.getString("data")); break;
            case "Jocs": itemController.setText(dades.getString("descripcio")); break;
            case "Personatges": itemController.setText(dades.getString("nom_del_videojoc")); break;
            }

            // Afegeix la informació a la vista
           // info.getChildren().add(itemTemplate);
            // Estableix que la mida de itemTemplaate s'ajusti a la mida de info
                AnchorPane.setTopAnchor(itemTemplate, 0.0);
                AnchorPane.setRightAnchor(itemTemplate, 0.0);
                AnchorPane.setBottomAnchor(itemTemplate, 0.0);
                AnchorPane.setLeftAnchor(itemTemplate, 0.0);

                } catch (Exception e) {
                System.out.println("ControllerDesktop: Error showing info.");
                System.out.println(e);
                }
            }



}


    


