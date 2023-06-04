package Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerController {

    @FXML
    private Canvas canvas;

    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 200;
    private static int NUM_FRAMES = 0;
    private static final int FRAME_DURATION = 100000000; // Duration in nanoseconds

    private List<Image> frames;
    private int currentFrameIndex;
    private long lastFrameTime;

    // Hier wird der AnimationTimer initialisiert
    private AnimationTimer timer;
    private boolean isMoving = false;

    public void initialize() {
        NUM_FRAMES = 8;
        // Load the sprite frames
        frames =  new ArrayList<>();
        for (int i = 1; i <= NUM_FRAMES; i++) {
            String imagePath = "/images/sprites/char1/Idle_" + i + ".png";
            Image frame = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            frames.add(frame);
        }

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
                        currentFrameIndex = (currentFrameIndex + 1) % NUM_FRAMES;

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

    private double playerVelocityX = 0;
    private double playerVelocityY = 0;
    private double playerPositionX;
    private double playerPositionY;

    // Hier werden die Tasteneingaben des Spielers erfasst
    @FXML
    void handleKeyPressed(KeyEvent event) {

        // Hier werden die Geschwindigkeit und die Position des Spielers initialisiert
        double playerSpeed;
        playerSpeed = 1;
        if (event.getCode() == KeyCode.A) {

            playerVelocityX = -playerSpeed;
        } else if (event.getCode() == KeyCode.D) {
            isMoving = true;
            playerVelocityX = playerSpeed;
        } else if (event.getCode() == KeyCode.W) {
            playerVelocityY = -playerSpeed;
        } else if (event.getCode() == KeyCode.S) {
            playerVelocityY = playerSpeed;
        }
    }

    // Hier werden die Tasteneingaben des Spielers aufgehoben
    @FXML
    void handleKeyReleased(KeyEvent event) {

        if (event.getCode() == KeyCode.A) {
            playerVelocityX = 0;
        } else if (event.getCode() == KeyCode.D) {
            playerVelocityX = 0;
        } else if (event.getCode() == KeyCode.W) {
            playerVelocityY = 0;
        } else if (event.getCode() == KeyCode.S) {
            playerVelocityY = 0;
        }
    }

    // Hier wird der Mausklick des Spielers erfasst
    @FXML
    void handleMouseClicked(MouseEvent event) {
        // Hier wird die Angriffsanimation des Spielers ausgelöst
        // ...

    }

    // Hier wird der Timer gestoppt
    public void stop() {
        timer.stop();
    }

}
