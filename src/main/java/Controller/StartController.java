package Controller;

import View.FxmlView;
import View.FxmlViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import View.Main;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeText;
    private FxmlView View;
    private WinController winController;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void exit(){
        Main.exit();
    }

    @FXML
    protected void openNameMenu() {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/NameMenu.fxml", 1920, 1080);
            // Retrieve the FXMLLoader from the factory
            FXMLLoader loader = factory.getLoader();

            // Show the scene and stage
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FxmlView.setScenefromXML("/fxml/NameMenu.fxml");
    }

    @FXML
    protected void openHighscoreMenu() {
//        View = new FxmlView();
//        View.load("/fxml/HighscoreMenu.fxml", "Highscore");
        /*FxmlView.setScenefromXML("/fxml/HighscoreMenu.fxml");
        winController = FxmlView.loader.getController();
        winController.setScene(FxmlView.getScene());*/
//        winController.showTable();
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/HighscoreMenu.fxml", 1920, 1080);
            // Retrieve the FXMLLoader from the factory
            FXMLLoader loader = factory.getLoader();
            winController = loader.getController();
            winController.setScene(scene);

            // Show the scene and stage
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}