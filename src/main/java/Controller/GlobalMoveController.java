package Controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class GlobalMoveController extends Thread {

    public List Listener =new ArrayList();


    private double jump_amount= 50;
    double GRAVITY = 2;
    private double velocityN1 =0;
    private double velocityN2 =0;
    private Node N1;
    private Node N2;
    private Scene S;



    private boolean moveRightN1;
    private boolean moveLeftN1;

    private boolean moveRightN2;
    private boolean moveLeftN2;
    private static final double NODE_SPEED = 10.0;

    private boolean isCollision=false;

    private AnimationTimer translateTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
                if(checkCollision()){
                    N1.setTranslateX(N1.getTranslateX()-NODE_SPEED);
                }
                if (moveRightN1) {
                    N1.setTranslateX((N1.getTranslateX() + NODE_SPEED));
                }
                if (moveLeftN1) {
                    N1.setTranslateX((N1.getTranslateX() - NODE_SPEED));
                }
                if (moveRightN2) {
                    N2.setTranslateX((N2.getTranslateX() + NODE_SPEED));
                }
                if (moveLeftN2) {
                    N2.setTranslateX((N2.getTranslateX() - NODE_SPEED));
                }
            }
    };

    private AnimationTimer jumpTimer1= new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Anwenden der Schwerkraft
            velocityN1 += GRAVITY;
            N1.setTranslateY(N1.getTranslateY() + velocityN1);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N1.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                velocityN1 = 0;
                N1.setTranslateY(0);
            }
        }
    };

    private AnimationTimer jumpTimer2 = new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Anwenden der Schwerkraft
            velocityN2 += GRAVITY;
            N2.setTranslateY(N2.getTranslateY() + velocityN2);
            // Überprüfen, ob das Rechteck den Boden berührt
            if (N2.getTranslateY() >=0) {
                // Zurücksetzen der Vertikalgeschwindigkeit
                velocityN2 = 0;
                N2.setTranslateY(0);
            }
        }
    };


    public GlobalMoveController(Node N1, Node N2, Scene S){
        this.N1 =N1;
        this.N2 =N2;
        this.S=S;
    }



    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPressD);
        this.S.setOnKeyReleased(this::handleKeyReleaseD);
        this.setTranslate();
        this.setJump1();
        this.setJump2();
    }


    private boolean handleKeyPressD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
             moveRightN1 = true;
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = true;
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.W) {
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            velocityN1 = -jump_amount;
//            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.L) {
            moveRightN2 = true;
//            System.out.println(this.getState());

        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = true;
//            System.out.println(this.getState());

        }
        if (event.getCode() == KeyCode.I ){
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            velocityN2 = -jump_amount;
//            System.out.println(this.getState());
        }
        return true;
    }

    private void handleKeyReleaseD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRightN1 = false;
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = false;
        }
        if (event.getCode() == KeyCode.L) {
            moveRightN2 = false;
        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = false;
        }
    }

    public boolean setTranslate(){
        this.translateTimer.start();
        return true;
    }

    public boolean setJump1(){
        jumpTimer1.start();
        return true;
    }

    public boolean setJump2(){
        jumpTimer2.start();
        return true;
    }

    public boolean checkCollision() {
        System.out.println(this.isCollision);
        // Überprüfe, ob die Formen tatsächlich kollidieren
        if (this.N1.getBoundsInParent().intersects(this.N2.getBoundsInParent())) {
            this.isCollision = true;
            return true;
        } else {
            this.isCollision = false;
            return false;
        }
    }

    public boolean checkCollision2() {
        System.out.println(this.isCollision);
        // Überprüfe, ob die Formen tatsächlich kollidieren
        if (this.N1.getBoundsInParent().intersects(this.N2.getBoundsInParent())) {
            this.isCollision = true;
            return true;
        } else {
            this.isCollision = false;
            return false;
        }
    }

}
