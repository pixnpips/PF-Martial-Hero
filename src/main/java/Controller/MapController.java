package Controller;

import View.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MapController  {


    @FXML
    private VBox background;

    @FXML
    AnchorPane SP1;

    private boolean paused = false;

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

        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);

        Canvas C1 = new Canvas(1000, 500);
        C1.setLayoutX(900); // X-Koordinate: 1200 Pixel
        C1.setLayoutY(300); // Y-Koordinate: 400 Pixel

        SpriteAnimationController SAC1= new SpriteAnimationController(C1,1);
        SAC1.initialize();
        SP1.getChildren().add(C1);

        Canvas C2 = new Canvas(1000, 500);
        C2.setLayoutX(1700); // X-Koordinate: 1200 Pixel
        C2.setLayoutY(300); // Y-Koordinate: 400 Pixel

        SP1.getChildren().add(C2);
        SpriteAnimationController SAC2= new SpriteAnimationController(C2,2);
        SAC2.initialize();


        GlobalMoveController PC= new GlobalMoveController(C1,C2,scene,SAC1,SAC2);
        PC.start();

        MapController MC1=fxmlLoader.getController();
        MC1.chooseMap(mapNr);
        preparePause(scene);


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

    @FXML
    protected void preparePause(Scene scene){
        AnchorPane pause = (AnchorPane) scene.lookup("#pause");
        pause.setVisible(false);
        EventHandler<KeyEvent> pauseHandler = new EventHandler<>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE){
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
                }
            }
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }

    @FXML
    protected void exit(){
        Main.startStage.close();
    }

    @FXML
    protected void initialize() {

    }

}