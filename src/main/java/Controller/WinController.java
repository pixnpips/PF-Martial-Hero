package Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class WinController {

    private String name;
    public Scene scene;
    @FXML
    private Label nameLabel;
    public WinController(){
    }
    public void setName(String name){
        this.name = name;
        nameLabel = (Label) scene.lookup("#name");
        nameLabel.textProperty().set(name);
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }

    /*private String name;
    private Label nameLabel;
    private Scene scene;
    @FXML
    protected void init() {
    }

    public WinController(Scene scene){
        this.scene = scene;
    }
    public void setWinnerName(String name){
        nameLabel = (Label) scene.lookup("#name");
        nameLabel.textProperty().set(name);
    }*/
}
