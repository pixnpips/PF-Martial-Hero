package Controller;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PauseController {

    @FXML
    private static AnchorPane pause;

    private static boolean paused;

    private EventHandler<KeyEvent> pauseHandler;
    //private MapController mapController = new MapController();
    private MapController mapController;

    public PauseController(){
        mapController = new MapController();
    }
    public void preparePause(Scene scene){
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
            System.out.println("Paused: "+ paused);
            return;
        }
        else if(paused){

            pause.setVisible(false);
            System.out.println("ESCAPE, unpause");
            paused = false;
            return;
        }
    }

    @FXML
    private void StartMenu(){
        //
    }
    @FXML
    private void exit(){
        mapController.exit();
    }
}
