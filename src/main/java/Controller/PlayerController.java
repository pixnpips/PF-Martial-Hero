package Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerController {

    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 200;
    private static final int FRAME_DURATION = 75000000; // Duration in nanoseconds
    private static final int NUM_FRAMES_IDLE = 8;
    private static final int NUM_FRAMES_RUN = 8;
    private static final int NUM_FRAMES_ATTACK = 6;
    private static final int NUM_FRAMES_TAKE_HIT = 4;
    private static final int NUM_FRAMES_DEATH = 6;
    private static final int NUM_FRAMES_JUMP = 2;

    @FXML
    private Canvas canvas;

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
    private boolean isMoving = false;

    private double playerVelocityX = 0;
    private double playerVelocityY = 0;
    private double playerPositionX;
    private double playerPositionY;

    public void initialize() {
        // Load the sprite frames
        idleFrames = loadFrames("/images/sprites/char1/Idle_", NUM_FRAMES_IDLE);
        runFrames = loadFrames("/images/sprites/char1/Run_", NUM_FRAMES_RUN);
        attack1Frames = loadFrames("/images/sprites/char1/Attack1_", NUM_FRAMES_ATTACK);
        attack2Frames = loadFrames("/images/sprites/char1/Attack2_", NUM_FRAMES_ATTACK);
        takeHitFrames = loadFrames("/images/sprites/char1/Take Hit_", NUM_FRAMES_TAKE_HIT);
        deathFrames = loadFrames("/images/sprites/char1/Death_", NUM_FRAMES_DEATH);
        jumpFrames = loadFrames("/images/sprites/char1/Jump_", NUM_FRAMES_JUMP);

        frames = idleFrames;

        {
            // Create the animation timer
            timer = new AnimationTimer() {
                @Override
                public void handle(long currentTime) {
                    // Calculate elapsed time since the last frame
                    long elapsedTime = currentTime - lastFrameTime;

                    // If enough time has passed, switch to the next frame
                    if (elapsedTime > FRAME_DURATION) {
                        // Get the GraphicsContext from the Canvas
                        GraphicsContext gc = canvas.getGraphicsContext2D();

                        // Clear the canvas
                        gc.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

                        // Draw the current frame
                        gc.drawImage(frames.get(currentFrameIndex), 0, 0);

                        // Update the frame index
                        currentFrameIndex = (currentFrameIndex + 1) % frames.size();

                        // Remember the current time for the next frame
                        lastFrameTime = currentTime;
                    }
                    // Hier wird die Position des Spielers aktualisiert
                    playerPositionX += playerVelocityX;
                    //playerPositionY += playerVelocityY;

                    // Hier wird die Position des Spielers gesetzt
                    canvas.setTranslateX(playerPositionX);
                    //canvas.setTranslateY(playerPositionY);

                    // Hier wird die Kollision mit dem Gegner überprüft
                    //if (playerImageView.getBoundsInParent().intersects(enemyImageView.getBoundsInParent())) {
                    // Hier wird die Kollisionsaktion ausgeführt
                    // ...

                }
            };
        }

        // Hier wird der Timer gestartet
        timer.start();
    }

    // Hier werden die Tasteneingaben des Spielers erfasst
    @FXML
    void handleKeyPressed(KeyEvent event) {

        // Hier werden die Geschwindigkeit und die Position des Spielers initialisiert
        double playerSpeed = 1;

        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
            isMoving = true;
            frames = runFrames;
            playerVelocityX = (event.getCode() == KeyCode.A) ? -playerSpeed : playerSpeed;
            canvas.setScaleX((event.getCode() == KeyCode.A) ? -1 : 1);
        } else if (event.getCode() == KeyCode.W) {
            playerVelocityY = -playerSpeed;
        } else if (event.getCode() == KeyCode.S) {
            playerVelocityY = playerSpeed;
        } else if (event.getCode() == KeyCode.SPACE) {
            //frames = jumpFrames;
        }
    }

    // Hier werden die Tasteneingaben des Spielers aufgehoben
    @FXML
    void handleKeyReleased(KeyEvent event) {

        if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
            isMoving = false;
            frames = idleFrames;
            playerVelocityX = 0;
        } else if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
            playerVelocityX = 0;
        } else if (event.getCode() == KeyCode.SPACE) {
            playerVelocityX = 0;
        }
    }

    // Hier wird der Mausklick des Spielers erfasst
    @FXML
    void handleMouseClicked(MouseEvent event) {
        // Hier wird die Angriffsanimation des Spielers ausgelöst

    }

    private List<Image> loadFrames(String imagePathPrefix, int numFrames) {
        // Load the sprite frames
        List<Image> frames = new ArrayList<>();
        for (int i = 1; i <= numFrames; i++) {
            String imagePath = imagePathPrefix + i + ".png";
            Image frame = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            frames.add(frame);
        }
        return frames;
    }

    // Hier wird der Timer gestoppt
    public void stopAnimation() {
        timer.stop();
        frames = idleFrames;
        currentFrameIndex = 0;
    }

}