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

    public static Player player1;
    public static Player player2;

    private FxmlView view;
    private DatabaseController databaseController;

    public PlayerController() {
        databaseController = new DatabaseController();
    }

    public void getNames() {
        name1 = input1.getText();
        name2 = input2.getText();
        if (name1.isEmpty() || name2.isEmpty()) {
            System.out.println("no input");
        } else {
            if (playerExists(name1)) {
                // Player aus der Datenbank abrufen
                player1 = getPlayerByName(name1);
            }
            if (playerExists(name2)) {
                // Player aus der Datenbank abrufen
                player2 = getPlayerByName(name2);
            } else {
                int wins = 0;
                player1 = new Player(name1, wins);
                player2 = new Player(name2, wins);
                // Player in die Datenbank einfügen
                databaseController.insertPlayer(player1);
                databaseController.insertPlayer(player2);
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
        FxmlView.setScenefromXML("/fxml/MapChoice.fxml");
    }

    protected boolean playerExists(String name) {
        // Überprüfen, ob der Player in der Datenbank existiert
        return databaseController.playerExists(name);
    }

    protected Player getPlayerByName(String name) {
        // Player anhand des Namens aus der Datenbank abrufen
        return databaseController.getPlayerByName(name);
    }
}