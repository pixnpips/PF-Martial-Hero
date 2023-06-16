package Controller;

import Model.Sprite;
import Model.Spritefactory;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class SpriteAnimationController {

    private Sprite S;

    private Canvas canvas;

    private int currentFrameIndex;
    private long lastFrameTime;

    private static final int FRAME_DURATION = 75000000; // Duration in nanoseconds
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;

        // Hier wird der AnimationTimer initialisiert
        private AnimationTimer timer;


        //Dieser Konstruktor wird es werden, den muss ihc erstellen

        public SpriteAnimationController(Canvas C, int num){
            this.canvas=C;
            if(num==1){this.S= Spritefactory.constructSprite(1);
            } else if(num==2){this.S= Spritefactory.constructSprite(2);
            }
        }

        // Hier werden sämtliche Animationen als Arraylisten in das Sprite Modell als Objektattribute geladen

        public void initialize() {

            // Load the sprite frames
            List<Image> currentFrames= this.S.getIdleFrames();
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
                            gc.setFill(Color.BLUE);
                            gc.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

                            // Draw the current frame
                            gc.drawImage(currentFrames.get(currentFrameIndex),-560,-520,1500,1500);

                            // Update the frame index
                            currentFrameIndex = (currentFrameIndex + 1) % currentFrames.size();

                            // Remember the current time for the next frame
                            lastFrameTime = currentTime;
                        }
                    }
                };
            }

            // Hier wird der Timer gestartet
            timer.start();
        }

    protected void turn(){
        this.canvas.setScaleX(-1);
    }
    public String getName(){
        return ("Ich bin Animationcontroller1");
    }


        // Hier wird der Timer gestoppt
        public void stopAnimation() {
            timer.stop();
            this.S.setFrames(this.S.getIdleFrames());
            currentFrameIndex = 0;
        }


}
