package Model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;
import Controller.MapController;

public class Timer {
    private int startSeconds = 50;
    private Timeline timeline;
    public final IntegerProperty secondsLeft = new SimpleIntegerProperty(startSeconds);
    @FXML
    private static Scene scene;
    @FXML
    private static Label count;
    private MapController mapController;

    public Timer(){
    }
    public void prepareTimer(Scene scene){
        mapController = new MapController();
        this.scene = scene;
        count = (Label) scene.lookup("#count");
        count.textProperty().bind(secondsLeft.asString());
        startTimer(startSeconds);
    }
    public void startTimer(int seconds){
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);
        secondsLeft.set(seconds);
        timeline.play();
    }
    protected void updateTimer(){
        int s = secondsLeft.get();
        if(s > 0){
            secondsLeft.set(s-1);
        }
        else if (s == 0){
            mapController.endGame();
        }
    }
    public void stopTimer(){
        timeline.stop();
    }
}
