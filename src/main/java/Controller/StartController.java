package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import Application.Main;

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
        Main.startStage.close();
    }

    @FXML
    protected void openMapChoice() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/MapChoice.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        Main.startStage.setTitle("Choose your Map");
        Main.startStage.setScene(scene);
        Main.startStage.show();
    }

}