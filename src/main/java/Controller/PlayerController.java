package Controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlayerController {

    @FXML
    private ImageView playerImageView;

    // Hier werden die Tasteneingaben des Spielers erfasst
    @FXML
    void handleKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.A) {
            playerVelocityX = -playerSpeed;
        } else if (event.getCode() == KeyCode.D) {
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

    // Hier werden die Geschwindigkeit und die Position des Spielers initialisiert
    private double playerSpeed = 5;
    private double playerVelocityX = 0;
    private double playerVelocityY = 0;
    private double playerPositionX;
    private double playerPositionY;

    // Hier wird der AnimationTimer initialisiert
    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            // Hier wird die Position des Spielers aktualisiert
            playerPositionX += playerVelocityX;
            playerPositionY += playerVelocityY;

            // Hier wird die Position des Spielers gesetzt
            playerImageView.setX(playerPositionX);
            playerImageView.setY(playerPositionY);

            // Hier wird die Kollision mit dem Gegner überprüft
            //if (playerImageView.getBoundsInParent().intersects(enemyImageView.getBoundsInParent())) {
            // Hier wird die Kollisionsaktion ausgeführt
            // ...
            //}
        }
    };

    // Hier wird der Timer gestartet
    public void start() {
        timer.start();
    }

    // Hier wird der Timer gestoppt
    public void stop() {
        timer.stop();
    }

}
