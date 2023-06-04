package Controller;

import Application.Main;
import Model.Player;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class PlayerController extends Thread {
    private double jump_amount= 50;
    double GRAVITY = 2;
    private double velocity=0;
    private Node N;

    private int player;

    private Scene S;

    private boolean moveRight;
    private static final double NODE_SPEED = 5.0;


    public PlayerController(Node N, Scene S, int player){
        this.N=N;
        this.S=S;
        this.player=player;
    }


//    public void run() {
//        if (this.player==1){
//            addTransP1();
//        }else addTransP2();
//        setJump(this.N);
//        System.out.println(this.getName()+ "gestartet");
//    }

    public void run(){
        this.S.setOnKeyPressed(this::handleKeyPress);
        this.S.setOnKeyReleased(this::handleKeyRelease);
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRight = true;
        }
    }

    private void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.D) {
            moveRight = false;
        }
    }

    public void setRight(Node N){
        AnimationTimer animationTimer1 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (moveRight) {
                    N.setTranslateX((N.getTranslateX() + NODE_SPEED));
                }
            }
        };
        animationTimer1.start();
    }



    public void setJump(Node n){
        // Animationsschleife, um den Sprung und die Schwerkraft anzuwenden
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
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

/*    private void addTransP1(){
        this.S.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.D) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                this.N.setTranslateX(this.N.getTranslateX() + 10);
            }
            if (event.getCode() == KeyCode.A) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                this.N.setTranslateX(this.N.getTranslateX() - 10);
            }
            if (event.getCode() == KeyCode.W) {
                // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
                velocity= -jump_amount;
            }
        });
    }*/

/*    private void addTransP2(){
        this.S.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                this.N.setTranslateX(this.N.getTranslateX() + 10);
            }
            if (event.getCode() == KeyCode.LEFT) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                this.N.setTranslateX(this.N.getTranslateX() - 10);
            }
            if (event.getCode() == KeyCode.UP) {
                // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
                velocity= -jump_amount;
            }
        });
    }*/
}
