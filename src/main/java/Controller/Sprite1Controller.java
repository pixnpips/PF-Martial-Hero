package Controller;

import Model.Sprite;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sprite1Controller {

    private Sprite S;

        private static final int FRAME_WIDTH = 400;
        private static final int FRAME_HEIGHT = 500;
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
                            GraphicsContext gc = canvas1.getGraphicsContext2D();

                            // Clear the canvas
                            gc.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

                            gc.setFill(Color.BLUE);
                            gc.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

                            // Draw the current frame
                            gc.drawImage(frames.get(currentFrameIndex),-560,-520,1500,1500);

                            // Update the frame index
                            currentFrameIndex = (currentFrameIndex + 1) % frames.size();

                            // Remember the current time for the next frame
                            lastFrameTime = currentTime;
                        }

                    }
                };
            }

            // Hier wird der Timer gestartet
            timer.start();
        }


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


