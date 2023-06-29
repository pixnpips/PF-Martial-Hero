package Controller;

import Model.Player;
import View.FxmlView;
import View.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;

public class WinController {

    //private String name;
    private Scene scene;
    @FXML
    private Label winnerName;
    private FxmlView View;

    public WinController(){
    }
    public void initialize(Scene scene){
        this.scene = scene;
        setName();
    }
    public void setName(){
        //Scene scene = FxmlView.getScene();
        //this.name = name;
        winnerName = (Label) scene.lookup("#name");
        if(getWinner()!=null){
            winnerName.textProperty().set(getWinner().getName());
        }
        else{
            winnerName.textProperty().set("Unentschieden");
        }
    }
    public void openStartMenu() throws IOException {
//        View = new FxmlView();
//        View.load("/fxml/StartMenu.fxml", "Martial Hero");
        FxmlView.setScenefromXML("/fxml/StartMenu.fxml");
    }
    public void openHighscoreMenu() throws IOException{
//        View = new FxmlView();
//        View.load("/fxml/HighscoreMenu.fxml", "Highscore");
        FxmlView.setScenefromXML("/fxml/HighscoreMenu.fxml");
    }
    public void exit(){
        Main.exit();
    }

    public Player getWinner(){
        if(PlayerController.player1.getEnergy()>PlayerController.player2.getEnergy()){
            return PlayerController.player1;
        } else if (PlayerController.player2.getEnergy() > PlayerController.player1.getEnergy()) {
            return PlayerController.player2;
        }
        else{return null;}
    }

}
