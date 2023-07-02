package Controller;

import View.FxmlViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import View.Main;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeText;
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
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openHighscoreMenu() {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/HighscoreMenu.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            winController = loader.getController();
            winController.setScene(scene);
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}