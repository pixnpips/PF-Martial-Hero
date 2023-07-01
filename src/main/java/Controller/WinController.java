package Controller;

import Model.Player;
import View.FxmlView;
import View.Main;
import javafx.fxml.FXML;
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
    private FxmlView View;
    DatabaseController DBC = DatabaseController.getInstance();

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
        DBC.updateWins(getWinner().getName());
        if(getWinner()!=null){
            winnerName.textProperty().set(getWinner().getName());
        }
        else{
            winnerName.textProperty().set("Unentschieden");
        }
    }
    public void openMapChoice() throws IOException {
        FxmlView.setScenefromXML("/fxml/MapChoice.fxml");
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
    public void setScene(Scene scene){this.scene=scene;}

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
