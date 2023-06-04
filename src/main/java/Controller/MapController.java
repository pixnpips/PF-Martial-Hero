package Controller;

import Application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class MapController  {

    @FXML
    protected void pause(){
        Main.startStage.close();
    }

    @FXML
    private VBox background;

    public void chooseMap(int mapNr){

        if(mapNr==1){
            background.getStyleClass().add("map01");
        } else {
            background.getStyleClass().add("map02");
        }
    }



    @FXML
    protected void openMap1()  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        System.out.println(fxmlLoader.getLocation());

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        ScrollPane root=fxmlLoader.getRoot();
        //System.out.println(fxmlLoader.getRoot().toString());

        Node VB1=root.getContent();
        Node N1= VB1.lookup("#c1");
        Node N2= VB1.lookup("#c2");

        System.out.println(N1.getClass());
        System.out.println(N2.getClass());

//        Player1Controller PC1= new Player1Controller(N1, scene, 1);
//        Player2Controller PC2= new Player2Controller(N2, scene, 2);
//
//        PC1.start();
//        PC2.start();

//        System.out.println(PC1.getState());
//        System.out.println(PC2.getState());

        PlayerController PC= new PlayerController(N1,N2,scene);
        PC.start();

        Main.startStage.setScene(scene);
        Main.startStage.show();

//        Wohin mit den Playern?!

//        Player player1= new Player("P1", N1);
//        Player player2= new Player("P2", N2);

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(1);
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

}