package Controller;

import View.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

public class MapController  {

    @FXML
    private VBox background;

    /*@FXML
    private Label count;*/
    private boolean paused = false;

    public void chooseMap(int mapNr){
        if(mapNr==1){
            background.getStyleClass().add("map01");
        } else {
            background.getStyleClass().add("map02");
        }
    }
    @FXML
    public void map1() throws IOException {
        openMap(1);
    }
    @FXML
    public void map2() throws IOException {
        openMap(2);
    }

    @FXML
    protected void openMap(int mapNr)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        System.out.println(fxmlLoader.getLocation());

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        Node N1 = scene.lookup("#canvas1");
        Node N2 = scene.lookup("#canvas2");

        GlobalMoveController PC= new GlobalMoveController(N1,N2,scene);
        PC.start();

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(mapNr);
        preparePause(scene);

        Main.startStage.setScene(scene);

        System.out.println(scene.getWindow().getWidth());
        System.out.println(scene.getWindow().getHeight());

        scene.getWindow().setWidth(1920);
        scene.getWindow().setHeight(1080);

        System.out.println(scene.getWindow().getWidth());
        System.out.println(scene.getWindow().getHeight());

        Main.startStage.setResizable(false);

        Main.startStage.hide();
        Main.startStage.show();

//        Main.startStage.setFullScreen(true);
//
//        System.out.println(scene.getWindow().getWidth());
//        System.out.println(scene.getWindow().getHeight());


        //prepareTimer(scene);
    }

    /*@FXML
    protected void prepareTimer(Scene scene){
        Label count = (Label) scene.lookup("#count");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int i = 60;
            public void run(){
                if (i >= 0) {
                    count.setText(""+i);
                    i--;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }*/

    @FXML
    protected void preparePause(Scene scene){
        AnchorPane pause = (AnchorPane) scene.lookup("#pause");
        pause.setVisible(false);
        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    if(paused==false){
                        paused = true;
                        pause.setVisible(true);
                        System.out.println("ESCAPE, pause");
                        return;
                    }
                    if(paused==true){
                        paused = false;
                        pause.setVisible(false);
                        System.out.println("ESCAPE, unpause");
                        return;
                    }
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

}