package Controller;

import View.FxmlView;
import View.Main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class MapController  {

    @FXML
    private VBox background;

    private GlobalMoveController PC;

    private int startSeconds = 6;
    private Timeline timeline;
    private boolean paused = false;
    private final IntegerProperty secondsLeft = new SimpleIntegerProperty(startSeconds);
    private Scene scene;
    private FxmlView View = new FxmlView();

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

        //GlobalMoveController PC = new GlobalMoveController(N1,N2,scene);
        PC = new GlobalMoveController(N1,N2,scene);
        PC.start();

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

        System.out.println(scene.getWindow().getWidth());
        System.out.println(scene.getWindow().getHeight());
        this.scene = scene;

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(mapNr);
        preparePause();
        background = (VBox) scene.lookup("#background");
        System.out.println(background.toString());
        PC.setBackground(background);
        prepareTimer();
    }
    protected void updateTimer(){
        int s = secondsLeft.get();
        if(s > 0){
            secondsLeft.set(s-1);
        }
        else if (s == 0){
            endGame();
        }
    }

    private void endGame() {
        stopTimer();
        exit();
        try {
            openWinMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void openWinMenu() throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/WinMenu.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        Main.startStage.setTitle("WINNER");
        Main.startStage.setScene(scene);
        Main.startStage.show();
        WinController WC = fxmlLoader.getController();*/
        View.load("/fxml/WinMenu.fxml", "WinMenu");
        WinController WC = new WinController();
        WC.setScene(View.getScene());
        WC.setName(getWinner());
    }

    private String getWinner() {
        ProgressBar hp01 = (ProgressBar) scene.lookup("#hp01");
        ProgressBar hp02 = (ProgressBar) scene.lookup("#hp02");
        String name ="";
        if(hp01.getProgress()>hp02.getProgress()){
            name = "Player 1";
        }
        else if(hp01.getProgress()<hp02.getProgress()){
            name = "Player 2";
        }
        else{
            name = "Unentschieden";
        }
        return name;
    }

    protected void startTimer(int seconds){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        secondsLeft.set(seconds);
        timeline.play();
    }

    protected void prepareTimer(){
        Label count = (Label) scene.lookup("#count");
        count.textProperty().bind(secondsLeft.asString());
        startTimer(startSeconds);
    }
    public void stopTimer(){
        timeline.stop();
    }

    @FXML
    protected void preparePause(){
        AnchorPane pause = (AnchorPane) scene.lookup("#pause");
        pause.setVisible(false);
        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    if(!paused){
                        paused = true;
                        //pauseGMC(true);
                        pause.setVisible(true);
                        stopTimer();
                        System.out.println("ESCAPE, pause");
                        return;
                    }
                    if(paused){
                        paused = false;
                        //pauseGMC(false);
                        pause.setVisible(false);
                        startTimer(secondsLeft.intValue());
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

    /*protected void pauseGMC(boolean b){
        if(b){
            PC.interrupt();
        }
        if(!b){
            PC.start();
        }
    }*/

}