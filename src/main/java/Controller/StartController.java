package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import Application.Main_V;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void exit(){
        Main_V.startStage.close();
    }

    @FXML
    protected void openMapChoice() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main_V.class.getResource("/fxml/MapChoice.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        Main_V.startStage.setTitle("Choose your Map");
        Main_V.startStage.setScene(scene);
        Main_V.startStage.show();
    }

}