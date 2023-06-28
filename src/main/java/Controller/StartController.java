package Controller;

import View.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import View.Main;

import java.io.IOException;

public class StartController {

    @FXML
    private Label welcomeText;
    private FxmlView View;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void exit(){
        Main.exit();
    }

    @FXML
    protected void openNameMenu() throws IOException {
        View = new FxmlView();
        View.load("/fxml/NameMenu.fxml", "Choose Player Names");
    }

    @FXML
    protected void openHighscoreMenu() throws IOException {
        View = new FxmlView();
        View.load("/fxml/HighscoreMenu.fxml", "Highscore");
    }
}