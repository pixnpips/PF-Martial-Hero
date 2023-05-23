package Controller;

import Application.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


import java.io.IOException;

public class MapController {

    private Circle shape1;
    private Circle shape2;

    @FXML
    protected void pause(){
        Main.startStage.close();
    }

    protected void setPlayer(){

    }

    @FXML
    protected void openMap() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map1.fxml"));
        System.out.println(fxmlLoader.getLocation());

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        Player player1= new Player("P1", 1);
        Player player2= new Player("P2", 2);
        HBox root =fxmlLoader.getRoot();

        this.shape1 = player1.getShape();
        this.shape2 = player2.getShape();

        root.getChildren().add(this.shape1);
        root.getChildren().add(this.shape2);

        Main.startStage.setTitle("Choose your Map");
        Main.startStage.setScene(scene);
        Main.startStage.show();
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

//    @FXML
//    public void handleKeyPress(KeyEvent event) {
//        switch (event.getCode()) {
//            case UP:
//                objectA.moveUp();
//                break;
//            case DOWN:
//                objectA.moveDown();
//                break;
//            case LEFT:
//                objectA.moveLeft();
//                break;
//            case RIGHT:
//                objectA.moveRight();
//                break;
//            default:
//                // Handle other keys if needed
//                break;
//        }
//    }

}