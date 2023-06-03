package Controller;

import Application.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class MapController {

    private double jump_amount= 50;
    double GRAVITY = 2;
    private double velocity=0;

    @FXML
    protected void pause(){
        Main.startStage.close();
    }


    @FXML
    public VBox background;

    public void chooseMap(int mapNr){

        if(mapNr==1){
            background.getStyleClass().add("map01");
        } else {
            background.getStyleClass().add("map02");
        }
    }


    @FXML
    protected void openMap1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        System.out.println(fxmlLoader.getLocation());

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        ScrollPane root=fxmlLoader.getRoot();
        //System.out.println(fxmlLoader.getRoot().toString());

        Node VB1=root.getContent();
        Node N1= VB1.lookup("#c1");
        Node N2= VB1.lookup("#c2");

        System.out.println(N1.getClass());
        System.out.println(N2.getClass());

        Player player1= new Player("P1", N1);
        Player player2= new Player("P2", N2);

//       System.out.println(N1.getClass().toString());

        addMovement(scene,N1, N2);
        setJump(N1);

        Main.startStage.setScene(scene);
        Main.startStage.show();

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(1);
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

}