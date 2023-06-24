package Controller;

import Model.Player;
import View.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PlayerController {
    @FXML
    TextField input1;
    @FXML
    TextField input2;

    private String name1;
    private String name2;

    private static Player player1;
    private static Player player2;

    private FxmlView View;

    public PlayerController(){}

    public void getNames(){
        name1 = input1.getText();
        name2 = input2.getText();
        if (name1.isEmpty() || name2.isEmpty()){
            System.out.println("no input");
        }
        else{
            if(playerExists(name1)){
                //get Player from DB
                //player1 =
            }
            if(playerExists(name2)){
                //get Player from DB
                //player2 =
            }
            else{
                player1 = new Player(name1);
                player2 = new Player(name2);
            }
            System.out.println(name1);
            System.out.println(name2);
            try {
                openMapChoice();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected void openMapChoice() throws IOException {
        View = new FxmlView();
        View.load("/fxml/MapChoice.fxml", "Choose your Map");
    }
    protected boolean playerExists(String name){
        //Existiert der Name schon in der Datenbank?
        //return true;
        return false;
    }


}
