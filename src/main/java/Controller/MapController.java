package Controller;

import Application.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class MapController {

    private double jump_amount= 50;
    double GRAVITY = 2;
    private double velocity=0;

    @FXML
    protected void pause(){
        Main.startStage.close();
    }


    @FXML
    protected void openMap1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map1.fxml"));
        System.out.println(fxmlLoader.getLocation());

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        VBox root =fxmlLoader.getRoot();

        System.out.println(root.getChildren().get(0).getClass().toString());

        Node N1= root.lookup("#c1");
        Node N2= root.lookup("#c2");

        Player player1= new Player("P1", N1);
        Player player2= new Player("P2", N2);

        System.out.println(N1.getClass().toString());

        addMovement(scene,N1, N2);

        setAnimationtimer(N1);

//      Main.startStage.setTitle("Choose your Map");

        Main.startStage.setScene(scene);
        Main.startStage.show();
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

    public void moveRight(Node n){
        Double x= n.getTranslateX();
        n.setTranslateX(x+50);
    }

    public void moveLeft(Node n){
        Double x= n.getTranslateX();
        n.setTranslateX(x+50);
    }

    public void addMovement(Scene s, Node n1, Node n2){

        s.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.D) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                n1.setTranslateX(n1.getTranslateX() + 10);
            }
            if (event.getCode() == KeyCode.A) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                n1.setTranslateX(n1.getTranslateX() - 10);
            }
            if (event.getCode() == KeyCode.RIGHT) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                n2.setTranslateX(n2.getTranslateX() + 10);
            }
            if (event.getCode() == KeyCode.LEFT) {
                // Bewege den Kreis um MOVE_AMOUNT Pixel nach rechts
                n2.setTranslateX(n2.getTranslateX() - 10);
            }
            if (event.getCode() == KeyCode.W) {
                // Führe einen Sprung aus, wenn die Taste "w" gwedrückt wird
                velocity= -jump_amount;
            }
        });
    }

    public void setAnimationtimer(Node n){
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

}