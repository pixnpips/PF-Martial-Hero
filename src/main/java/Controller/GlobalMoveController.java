package Controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GlobalMoveController extends Thread {
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


    public GlobalMoveController(Node N1, Node N2, Scene S){
        this.N1 =N1;
        this.N2 =N2;
        this.S=S;
    }


    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPressD);
        this.S.setOnKeyReleased(this::handleKeyReleaseD);
        this.setTranslate();
        this.setJump1(this.N1);
        this.setJump2(this.N2);
    }


    private void handleKeyPressD(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRightN1 = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.A) {
            moveLeftN1 = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.W) {
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            velocityN1 = -jump_amount;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.L) {
            moveRightN2 = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.J) {
            moveLeftN2 = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.I ){
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            velocityN2 = -jump_amount;
            System.out.println(this.getState());
        }
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


    public void setTranslate(){
        AnimationTimer animationTimer1 = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
        animationTimer1.start();
    }

    public void setJump1(Node n){
        // Animationsschleife, um den Sprung und die Schwerkraft anzuwenden
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Anwenden der Schwerkraft
                velocityN1 += GRAVITY;
                n.setTranslateY(n.getTranslateY() + velocityN1);
                // Überprüfen, ob das Rechteck den Boden berührt
                if (n.getTranslateY() >=0) {
                    // Zurücksetzen der Vertikalgeschwindigkeit
                    velocityN1 = 0;
                    n.setTranslateY(0);
                }
            }
        };
        timer.start();
    }

    public void setJump2(Node n){
        // Animationsschleife, um den Sprung und die Schwerkraft anzuwenden
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Anwenden der Schwerkraft
                velocityN2 += GRAVITY;
                n.setTranslateY(n.getTranslateY() + velocityN2);
                // Überprüfen, ob das Rechteck den Boden berührt
                if (n.getTranslateY() >=0) {
                    // Zurücksetzen der Vertikalgeschwindigkeit
                    velocityN2 = 0;
                    n.setTranslateY(0);
                }
            }
        };
        timer.start();
    }
}
