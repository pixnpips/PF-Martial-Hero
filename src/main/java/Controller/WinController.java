package Controller;

import Model.Player;
import View.FxmlViewFactory;
import View.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class WinController {

    //private String name;
    private Scene scene;
    @FXML
    private Label winnerName;
    @FXML
    TableColumn names;
    @FXML
    TableView highscoreTable;

    @FXML
    TableColumn wins;
    DatabaseController DBC = new DatabaseController();

    public WinController(){
    }
    public void setName(){
        winnerName = (Label) scene.lookup("#name");
        DBC.updateWins(getWinner().getName());
        if(getWinner()!=null){
            winnerName.textProperty().set(getWinner().getName());
        }
        else{
            winnerName.textProperty().set("Unentschieden");
        }
    }
    public void openMapChoice() {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/MapChoice.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openStartMenu() {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/StartMenu.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openHighscoreMenu(){
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/HighscoreMenu.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        showTable();
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
    public void setScene(Scene scene){
        this.scene=scene;
        setName();
    }

//    public void showTable(){
//        highscoreTable = (TableView) scene.lookup("#highscoreTable");
//        List<String> players = DBC.getAllPlayers();
//        List<Integer> numbers=new ArrayList<>();
//        System.out.println(players);
//        TableColumn name = new TableColumn<>();
//        TableColumn wins = new TableColumn<>();
//
//        for(String player : players){
//            int winsn = DBC.getWinsByName(player);
//            numbers.add(winsn);
//            System.out.println(player + DBC.getWinsByName(player));
//
//            TableRow newRow = new TableRow();
//            // Füge die Zeile zur Tabelle hinzu
//            highscoreTable.getItems().add(newRow);
//        }
//    }

}
