

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
import javafx.scene.layout.VBox;



public class ControllerMobile0_copy implements Initializable {
    @FXML
    private VBox yPanel;
    @FXML
    public String titol= "";


  @FXML
  private void toViewPersonatges(ActionEvent event) {
      UtilsViews.setView("MobilePersonatges");
      titol = "Personatges";
  }
  @FXML
  private void toViewJocs(ActionEvent event) {
      UtilsViews.setView("MobileJocs");
      titol = "Jocs";
  }
  @FXML
  private void toViewConsoles(ActionEvent event) {
      UtilsViews.setView("MobileConsoles");
      titol = "Consoles";
  }

   @FXML
  private void back(ActionEvent event) {
      UtilsViews.setView("Mobile0");
  }

  public void showLoading() {

    // Esborrar la llista actual
    yPanel.getChildren().clear();

    // Afegeix un indicador de progrés com a primer element de la llista
    ProgressIndicator progressIndicator = new ProgressIndicator();
    yPanel.getChildren().add(progressIndicator);
    }
@Override
public void initialize(URL location, ResourceBundle resources) {
    
    loadList();
}

public void loadList() {

    // Obtenir l'opció seleccionada
    String opcio = titol;

    // Obtenir una referència a AppData que gestiona les dades
    AppData appData = AppData.getInstance();

    // Mostrar el missatge de càrrega
    showLoading();

    // Demanar les dades
    appData.load(opcio, (result) -> {
        if (result == null) {
          System.out.println("ControllerDesktop: Error loading data.");
        } else {
          // Cal afegir el try/catch a la crida de ‘showList’
          try {
            showList();
          } catch (Exception e) {
            System.out.println("ControllerDesktop: Error showing list.");
          }
        }
      });
    }
 public void showList() throws Exception {

    String opcioSeleccionada = titol;

    // Obtenir una referència a l'ojecte AppData que gestiona les dades
    AppData appData = AppData.getInstance();

    // Obtenir les dades de l'opció seleccionada
    JSONArray dades = appData.getData(opcioSeleccionada);
    // Carregar la plantilla
    URL resource = this.getClass().getResource("assets/template_list_item.fxml");

    // Esborrar la llista actual
    yPanel.getChildren().clear();

    // Carregar la llista amb les dades
    for (int i = 0; i < dades.length(); i++) {
        JSONObject consoleObject = dades.getJSONObject(i);

        if (consoleObject.has("nom")) {
            
            String nom = consoleObject.getString("nom");
            
            String imatge = "assets/images/" + consoleObject.getString("imatge");
        
            FXMLLoader loader = new FXMLLoader(resource);
            Parent itemTemplate = loader.load();
            ControllerListItem itemController = loader.getController();
            itemController.setText(nom);
            itemController.setImage(imatge);
            
            // Defineix el callback que s'executarà quan l'usuari seleccioni un element
            // (cal passar final perquè es pugui accedir des del callback)
            
            
            yPanel.getChildren().add(itemTemplate);
            
        }
    }
    
  }

}


