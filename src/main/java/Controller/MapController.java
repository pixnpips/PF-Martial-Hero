package Controller;

import View.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.layout.VBox;

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
        ScrollPane root=fxmlLoader.getRoot();

        Node VB1=root.getContent();

        Node N1= VB1.lookup("#canvas1");
        Node N2= VB1.lookup("#canvas2");

        GlobalMoveController PC= new GlobalMoveController(N1,N2,scene);
        PC.start();

        Main.startStage.setScene(scene);
        Main.startStage.show();

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(mapNr);
        preparePause(scene);
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
    protected void preparePause(Scene s){
        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    try {
                        map1();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (event.getCode() == KeyCode.SPACE){
                    try {
                        map2();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        s.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

}