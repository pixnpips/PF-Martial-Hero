package Controller;

import Model.Player;
import View.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MapController  {

    @FXML
    private VBox background;

    @FXML
    AnchorPane SpritePane;

    @FXML
    ScrollPane BackGroundScrollPane;

    @FXML ProgressBar hp01;

    @FXML ProgressBar hp02;

    @FXML
    Scene scene;

    @FXML AnchorPane pause;

    private PauseController pauseController;

    private GlobalMoveController GMC;
    private SpriteAnimationController spriteAnimationController1;
    private SpriteAnimationController spriteAnimationController2;


    public void chooseMap(int mapNr){
        if(mapNr==1){
            background.getStyleClass().add("map01");
        } else {
            background.getStyleClass().add("map02");
        }
    }
    @FXML
    public void map1() throws IOException {
        openMap(1);
    }
    @FXML
    public void map2() throws IOException {
        openMap(2);
    }

    @FXML
    protected void openMap(int mapNr)  throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/Map.fxml"));
        System.out.println(fxmlLoader.getLocation());
        fxmlLoader.setController(this);

        scene = new Scene(fxmlLoader.load(), 1920, 1080);

        Canvas C1 = new Canvas(1000, 500);
        C1.setLayoutX(000); // X-Koordinate: 1200 Pixel
        C1.setLayoutY(300); // Y-Koordinate: 400 Pixelad
        SpritePane.getChildren().add(C1);
        spriteAnimationController1 = new SpriteAnimationController(C1,1);
        spriteAnimationController1.initialize();

        Player Player1= new Player("Player1");
        Player1.setHealthbar(this.hp01);
        Player Player2=new Player("Player 2");
        Player2.setHealthbar(this.hp02);


        Canvas C2 = new Canvas(1000, 500);
        C2.setLayoutX(1000); // X-Koordinate: 1200 Pixel
        C2.setLayoutY(270); // Y-Koordinate: 400 Pixel
        SpritePane.getChildren().add(C2);
        spriteAnimationController2 = new SpriteAnimationController(C2,2);
        spriteAnimationController2.initialize();

        GMC = new GlobalMoveController(C1,C2,scene,this.spriteAnimationController1, this.spriteAnimationController2);
        GMC.start();

        // Hier werden die PropertyChangeListener gesettet
        DamageController DC= new DamageController(GMC,spriteAnimationController1,spriteAnimationController2, Player1, Player2);
        GMC.addPropertyChangeListener(DC);
        spriteAnimationController1.addPropertyChangeListener(DC);
        spriteAnimationController2.addPropertyChangeListener(DC);

//        GMC.setx_N1(10);

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(mapNr);

        pauseController = new PauseController();
        pauseController.preparePause(scene);




        Main.startStage.setScene(scene);

        System.out.println(scene.getWindow().getWidth());
        System.out.println(scene.getWindow().getHeight());

        scene.getWindow().setWidth(1920);
        scene.getWindow().setHeight(1080);

        System.out.println(scene.getWindow().getWidth());
        System.out.println(scene.getWindow().getHeight());

        Main.startStage.setResizable(false);
        Main.startStage.hide();
        Main.startStage.show();

    }

    /*@FXML
    protected void preparePause(Scene scene){
        this.pause = (AnchorPane) scene.lookup("#pause");
        pause.setVisible(false);
        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
                    pause();
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }*/

    /*@FXML
    private void pause(){
        //AnchorPane pause = (AnchorPane) scene.lookup("#pause");
        //pause.setVisible(false);

        if(paused==false){
            paused = true;
            pause.setVisible(true);
            System.out.println("ESCAPE, pause");
            return;
        }
        if(paused==true){
            paused = false;
            pause.setVisible(false);
            System.out.println("ESCAPE, unpause");
            return;
        }
    }*/

    @FXML
    public void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {
    }

    /*public static boolean getPaused(){
        return paused;
    }
    private void setPaused(boolean b){
        paused = b;
    }*/

}