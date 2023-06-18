package Controller;

import View.FxmlView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import View.Main;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeText;
    private FxmlView View = new FxmlView();
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void openMapChoice() throws IOException {
        View.load("/fxml/MapChoice.fxml", "Choose your Map");
    }

}