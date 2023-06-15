package Model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.util.List;

public class Sprite {


    //konstante Parameter zur Anwendung des Animation Timers für Sprite interne Animationen!
    private String Name;

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_DURATION = 75000000; // Duration in nanoseconds
    private static final int NUM_FRAMES_IDLE = 8;
    private static final int NUM_FRAMES_RUN = 8;
    private static final int NUM_FRAMES_ATTACK = 6;
    private static final int NUM_FRAMES_TAKE_HIT = 4;
    private static final int NUM_FRAMES_DEATH = 6;
    private static final int NUM_FRAMES_JUMP = 2;


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


    public void setIdleFrames(List<Image> idleFrames) {
        this.idleFrames = idleFrames;
    }

    public void setRunFrames(List<Image> runFrames) {
        this.runFrames = runFrames;
    }

    public void setAttack1Frames(List<Image> attack1Frames) {
        this.attack1Frames = attack1Frames;
    }

    public void setAttack2Frames(List<Image> attack2Frames) {
        this.attack2Frames = attack2Frames;
    }

    public void setTakeHitFrames(List<Image> takeHitFrames) {
        this.takeHitFrames = takeHitFrames;
    }

    public void setDeathFrames(List<Image> deathFrames) {
        this.deathFrames = deathFrames;
    }

    public void setJumpFrames(List<Image> jumpFrames) {
        this.jumpFrames = jumpFrames;
    }

    public void setFrames(List<Image> frames) {
        this.frames = frames;
    }

    public void setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
    }

    public void setLastFrameTime(long lastFrameTime) {
        this.lastFrameTime = lastFrameTime;
    }


    // Konstruktor für den Sprite
    public Sprite(String n){
        this.Name=n;
    }
}
