package Controller;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class GlobalMoveController extends Thread {

    public List Listener =new ArrayList();

    private double jump_amount= 50;
    double GRAVITY = 2;
    private double node1JumpVelocity =0;
    private double Node2JumpVelocity =0;

    private Node N1;
    private Node N2;
    private Scene S;

    private boolean moveRightN1;
    private boolean moveLeftN1;

    private boolean moveRightN2;
    private boolean moveLeftN2;

    private boolean Airtime1=false;

    private boolean Airtime2=false;
    private static final double NODE_TRANSLATE_SPEED = 10.0;

    private boolean isCollision=false;

    private SpriteAnimationController SAC1;
    private SpriteAnimationController SAC2;


    private AnimationTimer translateTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
                if(!checkCollision()) {
                    if (moveRightN1) {
                        N1.setTranslateX((N1.getTranslateX() + NODE_TRANSLATE_SPEED));
                    }
                    if (moveLeftN1) {
                        N1.setTranslateX((N1.getTranslateX() - NODE_TRANSLATE_SPEED));

                    }
                    if (moveRightN2) {
                        N2.setTranslateX((N2.getTranslateX() + NODE_TRANSLATE_SPEED));

                    }
                    if (moveLeftN2) {
                        N2.setTranslateX((N2.getTranslateX() - NODE_TRANSLATE_SPEED));
                    }
                }
                else resetCollision();
            }
    };


    private AnimationTimer jumpTimer1= new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Anwenden der Schwerkraft
            node1JumpVelocity += GRAVITY;
            Airtime1=true;
            N1.setTranslateY(N1.getTranslateY() + node1JumpVelocity);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N1.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                node1JumpVelocity = 0;
                N1.setTranslateY(0);
                this.stop();
                SAC1.setIdle();
                Airtime1=false;
            }
        }
    };

    private AnimationTimer jumpTimer2 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Anwenden der Schwerkraft
            Airtime2=true;
            Node2JumpVelocity += GRAVITY;
            N2.setTranslateY(N2.getTranslateY() + Node2JumpVelocity);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N2.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                Node2JumpVelocity = 0;
                N2.setTranslateY(0);
                this.stop();
                SAC2.setIdle();
                Airtime2=false;
            }
        }
    };


    public GlobalMoveController(Node N1, Node N2, Scene S, SpriteAnimationController S1, SpriteAnimationController S2){
        this.N1 =N1;
        this.N2 =N2;
        this.S=S;
        this.SAC1=S1;
        this.SAC2=S2;
    }

    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPressD);
        this.S.setOnKeyReleased(this::handleKeyReleaseD);
        this.setTranslate();
    }

    private boolean handleKeyPressD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
             moveRightN1 = true;
            if(!Airtime1){SAC1.setRun();}
            SAC1.turn(false);
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = true;
            if(!Airtime1){SAC1.setRun();}
            SAC1.turn(true);
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.W) {
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            node1JumpVelocity = -jump_amount;
            SAC1.setJump();
            this.jumpTimer1.start();
        }
        if (event.getCode() == KeyCode.E) {
            SAC1.setAttack1();
        }
        if (event.getCode() == KeyCode.Q) {
            SAC1.setAttack2();
        }



        if (event.getCode() == KeyCode.L) {
            moveRightN2 = true;
            if(!Airtime2){SAC2.setRun();}
            SAC2.setBeginn(false);
            SAC2.turn(false);

        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = true;
            if(!Airtime2){SAC2.setRun();}
            SAC2.turn(true);
        }
        if (event.getCode() == KeyCode.I ){
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            SAC2.setJump();
            Node2JumpVelocity = -jump_amount;
            this.jumpTimer2.start();
//            System.out.println(this.getState());
        }

        if (event.getCode() == KeyCode.U) {
            SAC2.setAttack1();
        }
        if (event.getCode() == KeyCode.O) {
            SAC2.setAttack2();
        }
        return true;
    }

    private void handleKeyReleaseD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRightN1 = false;
            SAC1.setIdle();
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = false;
            SAC1.setIdle();
        }
        if (event.getCode() == KeyCode.L) {
            moveRightN2 = false;
            SAC2.setIdle();
        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = false;
            SAC2.setIdle();
        }
    }
    public boolean setTranslate(){
        this.translateTimer.start();
        return true;
    }

    public double [] getNodePositions(){
        return new double[] {N1.getBoundsInParent().getCenterX(),this.N1.getBoundsInParent().getCenterY(),this.N2.getBoundsInParent().getCenterX(), this.N2.getBoundsInParent().getCenterY()};
    }


    public boolean checkCollision() {
       // System.out.println(this.isCollision);
        // Überprüfe, ob die Formen tatsächlich kollidieren

        double[] positions= this.getNodePositions();


        if (positions[2]>positions[0] ) {
            this.isCollision = (positions[2]-positions[0]) < 200 && (Math.abs(positions[1]-positions[3]) < 500);
        }else if (positions[2]<positions[0]) {
            this.isCollision = (positions[0]-positions[2]) < 200 && (Math.abs(positions[1]-positions[3]) < 500);
        }
        return this.isCollision;


//        if (this.N1.getBoundsInParent().intersects(this.N2.getBoundsInParent())) {
//            this.isCollision = true;
//            return true;
//        } else {
//            this.isCollision = false;
//            return false;
//        }

    }

    private boolean resetCollision(){

        double N1x= this.N1.getBoundsInParent().getCenterX();
        double N2x= this.N2.getBoundsInParent().getCenterX();
        double N1y= this.N1.getTranslateY();
        double N2y= this.N2.getTranslateY();

        double velocity=1;

        if(N1y!=N2y){
            velocity=25;
        }

        if (N1x<N2x){
            this.N1.setTranslateX(N1.getTranslateX()-velocity);
            this.N2.setTranslateX(N2.getTranslateX()+velocity);
            return false;
        }
        if(N1x>N2x){
            this.N1.setTranslateX(N1.getTranslateX()+velocity);
            this.N2.setTranslateX(N2.getTranslateX()-velocity);
            return false;
        }
        return true;
    }

}
