package Controller;

import View.FxmlView;
import View.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class WinController {

    @FXML
    private String name;
    public Scene scene;
    @FXML
    private Label nameLabel;
    private FxmlView View;

    public WinController(){
        View = new FxmlView();
    }
    public void setName(String name){
        this.name = name;
        nameLabel = (Label) scene.lookup("#name");
        nameLabel.textProperty().set(name);
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    public void startMenu() throws IOException {
        View.load("/fxml/StartMenu.fxml", "Martial Hero");
    }
    public void exit(){
        Main.exit();
    }

}
