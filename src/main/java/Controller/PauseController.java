package Controller;

import Model.Timer;
import View.FxmlViewFactory;
import View.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class PauseController {

    @FXML
    private static AnchorPane pause;
    private static boolean paused;
    private static Timer timer;
    private EventHandler<KeyEvent> pauseHandler;

    public PauseController(){
    }
    public void preparePause(Scene scene, Timer timer){
        this.timer = timer;
        pause = (AnchorPane) scene.lookup("#pause");
        pause.setVisible(false);
        paused = false;

        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    pause();
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }

    @FXML
    private void pause(){
        System.out.println("Paused: "+ paused);

        if(!paused){
            pause.setVisible(true);
            System.out.println("ESCAPE, pause");
            paused = true;
            timer.stopTimer();
            System.out.println("Paused: "+ paused);
            return;
        }
        else if(paused){
            pause.setVisible(false);
            System.out.println("ESCAPE, unpause");
            paused = false;
            timer.startTimer(timer.secondsLeft.intValue());
            return;
        }
    }
    @FXML
    private void StartMenu() {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/StartMenu.fxml", 1920, 1080);
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exit(){
        Main.exit();
    }

    public boolean getPaused(){
        return this.paused;
    }
}
