package Model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprite {


    //konstante Parameter zur Anwendung des Animation Timers für Sprite interne Animationen!
    private String Name;
    public static final int NUM_FRAMES_IDLE = 8;
    public static final int NUM_FRAMES_RUN = 8;
    public static final int NUM_FRAMES_ATTACK = 6;
    public static final int NUM_FRAMES_TAKE_HIT = 4;
    public static final int NUM_FRAMES_DEATH = 6;
    public static final int NUM_FRAMES_JUMP = 2;

    //Bildsequenzen der einzelnen Animationen

    private List<Image> idleFrames;
    private List<Image> runFrames;
    private List<Image> attack1Frames;
    private List<Image> attack2Frames;
    private List<Image> takeHitFrames;
    private List<Image> deathFrames;
    private List<Image> jumpFrames;


    //aktuell darzustellende Bildsequenz

    private List<Image> frames;



    //Getter und setter Methoden für Frames der aktuellen Animationsdarstellung

    public void setFrames(List<Image> frames) {
        this.frames = frames;
    }

    public List<Image> getFrames() {
        return this.frames;
    }



    // Konstruktor für den Sprite
    public Sprite(String n, String pathToFrames, String idleFolder, String runFolder, String attack1Folder, String attack2Folder, String takeHitFolder, String deathFolder, String jumpFolder ){
        this.Name=n;
        this.idleFrames=this.loadFrames(pathToFrames.concat(idleFolder),NUM_FRAMES_IDLE);
        this.runFrames=this.loadFrames(pathToFrames.concat(runFolder),NUM_FRAMES_RUN);
        this.attack1Frames=this.loadFrames(pathToFrames.concat(attack1Folder),NUM_FRAMES_ATTACK);
        this.attack2Frames=this.loadFrames(pathToFrames.concat(attack2Folder),NUM_FRAMES_ATTACK);
        this.takeHitFrames=this.loadFrames(pathToFrames.concat(takeHitFolder),NUM_FRAMES_TAKE_HIT);
        this.deathFrames=this.loadFrames(pathToFrames.concat(deathFolder),NUM_FRAMES_DEATH);
        this.jumpFrames=this.loadFrames(pathToFrames.concat(jumpFolder),NUM_FRAMES_JUMP);
    }

    //Getter Methoden für alle Framesequenzen

    public List<Image> getIdleFrames() {
        return idleFrames;
    }

    public List<Image> getRunFrames() {
        return runFrames;
    }

    public List<Image> getAttack1Frames() {
        return attack1Frames;
    }

    public List<Image> getAttack2Frames() {
        return attack2Frames;
    }

    public List<Image> getTakeHitFrames() {
        return takeHitFrames;
    }

    public List<Image> getDeathFrames() {
        return deathFrames;
    }

    public List<Image> getJumpFrames() {
        return jumpFrames;
    }

    //Hier ist die Methode zum Laden der Frames in das Modell, sie wird innerhalb des Konstruktors aufgerufen

    private  List<Image> loadFrames(String imagePathPrefix, int numFrames) {
        // Load the sprite frames
        List<Image> frames = new ArrayList<>();
        for (int i = 1; i <= numFrames; i++) {
            String imagePath = imagePathPrefix + i + ".png";
            Image frame = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            frames.add(frame);
        }
        return frames;
    }
}
