package Controller;

import Model.Sprite;
import Model.Spritefactory;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

    private boolean attack1FramesLoaded;

    private boolean attack2FramesLoaded;


    private boolean turn;

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
                                turn=true;
                            }

                            if(turn){canvas.setScaleX(-1);}else{canvas.setScaleX(1);};
                          //temporär
//                            gc.setFill(Color.RED);
//                            gc.fillRect(0,0,FRAME_WIDTH,FRAME_HEIGHT);

                            // Draw the current frame
                            gc.drawImage(frames.get(currentFrameIndex),-100,-280,1200,1200);

                            // Update the frame index
                            currentFrameIndex = (currentFrameIndex + 1) % frames.size();

                            // Remember the current time for the next frame
                            lastFrameTime = currentTime;

                            if((attack1FramesLoaded||attack2FramesLoaded)&&currentFrameIndex==2){
                                setAttack1(true);
                                setAttack2(true);
                            }
                            if((attack1FramesLoaded||attack2FramesLoaded)&&currentFrameIndex==4){
                                setAttack1(false);
                                setAttack2(false);
                            }

                            if((attack1FramesLoaded||attack2FramesLoaded)&&currentFrameIndex>=5){
                                setIdle();
                                attack1FramesLoaded=false;
                                attack2FramesLoaded=false;
                            }
                        }
                    }
                };
            }
            // Hier wird der Timer gestartet
            moveAnimationTimer.start();
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

    public void setAttack1Frames() {
        this.frames=this.attack1Frames;
        this.attack1FramesLoaded=true;
        currentFrameIndex = 0;
    }

    public void setAttack2Frames() {
        this.frames=this.attack2Frames;
        this.attack2FramesLoaded=true;
        this.currentFrameIndex = 0;
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

    public boolean getturn() {return this.turn;
    }

    public void setturn(boolean  b) {
        boolean oldValue = this.turn;
        this.turn=b;
        this.changes.firePropertyChange("turn"+this.playerNum, oldValue, this.turn);
    }

    public int getPlayerNum(){
            return this.playerNum;
    }

}
