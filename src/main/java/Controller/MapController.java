package Controller;

import View.Main;
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
    protected void openMap()  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        ScrollPane root=fxmlLoader.getRoot();

        Node VB1=root.getContent();


        Node N1= VB1.lookup("#canvas1");
        Node N2= VB1.lookup("#canvas2");


        GlobalMoveController PC= new GlobalMoveController(N1,N2,scene);
        PC.start();

        Main.startStage.setScene(scene);
        Main.startStage.show();

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