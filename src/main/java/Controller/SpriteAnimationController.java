package Controller;

import Model.Sprite;
import Model.Spritefactory;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.beans.*;
import java.util.List;

public class SpriteAnimationController {

    private PropertyChangeSupport changes;

    private Sprite S;

    private int playerNum;

    private Canvas canvas;

    private List<Image>frames;

    private List<Image> idleFrames;
    private List<Image> runFrames;
    private List<Image> attack1Frames;
    private List<Image> attack2Frames;
    private List<Image> takeHitFrames;
    private List<Image> deathFrames;
    private List<Image> jumpFrames;

    private boolean attack1 =false;
    private boolean attack2 =false;

    private boolean beginn;
    private int currentFrameIndex;
    private long lastFrameTime;

    private static final int FRAME_DURATION = 75000000; // Duration in nanoseconds
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 500;

        // Hier wird der AnimationTimer initialisiert
        private AnimationTimer moveAnimationTimer;

    private AnimationTimer attackAnimationTimer;


        //Dieser Konstruktor wird es werden, den muss ihc erstellen

        public SpriteAnimationController(Canvas C, int num){

            this.playerNum=num;
            this.canvas=C;
            if(num==1){this.S= Spritefactory.constructSprite(1);
            } else if(num==2){this.S= Spritefactory.constructSprite(2);
            }
            this.idleFrames=this.S.getIdleFrames();
            this.runFrames=this.S.getRunFrames();
            this.attack1Frames=this.S.getAttack1Frames();
            this.attack2Frames=this.S.getAttack2Frames();
            this.takeHitFrames=this.S.getTakeHitFrames();
            this.deathFrames=this.S.getDeathFrames();
            this.jumpFrames=this.S.getJumpFrames();
            this.beginn=true;
            this.changes= new PropertyChangeSupport(this);
        }

        // Hier werden sämtliche Animationen als Arraylisten in das Sprite Modell als Objektattribute geladen


        public void initialize() {
            // Load the sprite frames
           this.frames = this.idleFrames;
            {
                // Create the animation timer
                this.moveAnimationTimer = new AnimationTimer() {
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

                            if(playerNum==2&&beginn){
                                turn(true);
                            }
                          //temporär
//                            gc.setFill(Color.RED);
//                            gc.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

                            // Draw the current frame
                            gc.drawImage(frames.get(currentFrameIndex),-100,-280,1200,1200);

                            // Update the frame index
                            currentFrameIndex = (currentFrameIndex + 1) % frames.size();

                            // Remember the current time for the next frame
                            lastFrameTime = currentTime;

                            if((attack1||attack2)&&currentFrameIndex>=5){
                                setIdle();
                                setAttack1(false);
                                setAttack2(false);
                            }
                        }
                    }
                };
            }
            // Hier wird der Timer gestartet
            moveAnimationTimer.start();
        }



    protected void turn(boolean b){
            if(b){this.canvas.setScaleX(-1);}else{this.canvas.setScaleX(1);};
    }

        // Hier wird der Timer gestoppt
        public void setIdle() {
            this.frames=this.idleFrames;
            currentFrameIndex = 0;
        }


    public void setJump() {
        this.frames=this.jumpFrames;
        currentFrameIndex = 0;
    }

    public void setRun() {
        this.frames=this.runFrames;
    }

    public void setAttack1() {
            this.frames=this.attack1Frames;
        currentFrameIndex = 0;
        setAttack1(true);
        if(this.currentFrameIndex>=this.frames.size()){
            this.setIdle();
            System.out.println("Atack beendet");
        }
    }

    public void setAttack2() {
        this.frames=this.attack2Frames;
        this.currentFrameIndex = 0;
        setAttack2(true);
       if(this.currentFrameIndex>=this.frames.size()){
           this.setIdle();
           System.out.println("Atack beendet");
       }

    }
    public void setGetHit() {
        this.frames=this.takeHitFrames;
        currentFrameIndex = 0;
    }

    public void setDead(){
            this.frames=this.deathFrames;
        currentFrameIndex = 0;
    }

    public void setBeginn(boolean b){
            this.beginn=b;
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public boolean getattack1() {
        return this.attack1;
    }
    public void setAttack1(boolean  b) {
        boolean oldValue = this.attack1;
        this.attack1=b;
        this.changes.firePropertyChange("attack1"+this.playerNum, oldValue, this.attack1);
    }

    public boolean getattack2() {return this.attack2;
    }
    public void setAttack2(boolean  b) {
        boolean oldValue = this.attack2;
        this.attack2=b;
        this.changes.firePropertyChange("attack2"+this.playerNum, oldValue, this.attack2);
    }

}
