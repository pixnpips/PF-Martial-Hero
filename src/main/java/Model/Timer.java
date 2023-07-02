package Model;

import Controller.MapController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class Timer {
    private int startSeconds = 30;
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
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> {
            try {
                updateTimer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        secondsLeft.set(seconds);
        timeline.play();
    }
    protected void updateTimer() throws IOException {
        int s = secondsLeft.get();
        if(s > 0){
            secondsLeft.set(s-1);
        }
        else if (s == 0){
            stopTimer();
            try {
                mapController.endGame();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void stopTimer(){
        timeline.stop();
    }

    public void resetTimer(){
        timeline.playFromStart();
        stopTimer();
    }
}
