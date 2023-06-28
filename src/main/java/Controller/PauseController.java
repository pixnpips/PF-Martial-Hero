package Controller;

import Model.Timer;
import View.FxmlView;
import View.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
    private FxmlView View;

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
    private void StartMenu() throws IOException {
//        Main.exit();
//        Main main = new Main();
//        try {
//            main.restart();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        FxmlView.setScenefromXML("/fxml/StartMenu.fxml");
    }

    @FXML
    private void exit(){
        Main.exit();
    }
}
