package Model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.util.List;

public class Sprite {

    private Node N;
    private String Name;
    private double velocity=0;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_DURATION = 75000000; // Duration in nanoseconds
    private static final int NUM_FRAMES_IDLE = 8;
    private static final int NUM_FRAMES_RUN = 8;
    private static final int NUM_FRAMES_ATTACK = 6;
    private static final int NUM_FRAMES_TAKE_HIT = 4;
    private static final int NUM_FRAMES_DEATH = 6;
    private static final int NUM_FRAMES_JUMP = 2;

    @FXML
    private Canvas canvas1;

    private List<Image> idleFrames;
    private List<Image> runFrames;
    private List<Image> attack1Frames;
    private List<Image> attack2Frames;
    private List<Image> takeHitFrames;
    private List<Image> deathFrames;
    private List<Image> jumpFrames;

    private List<Image> frames;
    private int currentFrameIndex;
    private long lastFrameTime;

    // Hier wird der AnimationTimer initialisiert
    private AnimationTimer timer;

    public Sprite(Node N, String Name){
        this.N=N;
        this.Name =Name;
    }

}
