package Controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Player2Controller extends Thread {
    private double jump_amount= 50;
    double GRAVITY = 2;
    private double velocity=0;
    private Node N;

    private int player;

    private Scene S;

    private boolean moveRight;
    private boolean moveLeft;
    private static final double NODE_SPEED = 10.0;


    public Player2Controller(Node N, Scene S, int player){
        this.N=N;
        this.S=S;
        this.player=player;
    }


    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPressD);
        this.S.setOnKeyReleased(this::handleKeyReleaseD);
        this.setRight(this.N);
        this.setJump(this.N);
    }


    private void handleKeyPressD(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT) {
            moveRight = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.LEFT) {
            moveLeft = true;
            System.out.println(this.getState());
        }
        if (event.getCode() == KeyCode.UP) {
            // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
            velocity= -jump_amount;
            System.out.println(this.getState());
        }
    }

    private void handleKeyReleaseD(KeyEvent event) {
        if (event.getCode() == KeyCode.RIGHT) {
            moveRight = false;
        }
        if (event.getCode() == KeyCode.LEFT) {
            moveLeft = false;
        }
    }

    public void setRight(Node N){
        AnimationTimer animationTimer1 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (moveRight) {
                    N.setTranslateX((N.getTranslateX() + NODE_SPEED));
                }
                if (moveLeft) {
                    N.setTranslateX((N.getTranslateX() - NODE_SPEED));
                }

            }
        };
        animationTimer1.start();
    }

    public void setJump(Node n){
        // Animationsschleife, um den Sprung und die Schwerkraft anzuwenden
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Anwenden der Schwerkraft
                velocity += GRAVITY;
                n.setTranslateY(n.getTranslateY() + velocity);
                // Überprüfen, ob das Rechteck den Boden berührt
                if (n.getTranslateY() >=0) {
                    // Zurücksetzen der Vertikalgeschwindigkeit
                    velocity = 0;
                    n.setTranslateY(0);
                }
            }
        };
        timer.start();
    }
}
