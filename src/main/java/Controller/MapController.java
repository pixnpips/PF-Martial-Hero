package Controller;

import Model.Timer;
import View.FxmlView;
import View.FxmlViewFactory;
import View.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MapController  {

    @FXML
    VBox background;

    @FXML
    AnchorPane SpritePane;

    @FXML
    ScrollPane BackGroundScrollPane;

    @FXML ProgressBar hp01;

    @FXML ProgressBar hp02;

    @FXML
    static Scene scene;

    private PauseController pauseController;

    private GlobalMoveController GMC;
    private SpriteAnimationController spriteAnimationController1;
    private SpriteAnimationController spriteAnimationController2;

    public static Timer timer;
    private WinController winController;
    private FxmlView View;

    @FXML
    Label p1name;

    @FXML
    Label p2name;


    public MapController(){
    }

    @FXML
    protected void openMap(int mapNr) {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            scene = factory.createSceneFromFXML("/fxml/Map.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            loader.setController(this);
            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        background = (VBox) scene.lookup("#background");
        chooseMap(mapNr);

        Canvas C1 = new Canvas(1000, 500);
        C1.setLayoutX(000); // X-Koordinate: 1200 Pixel
        C1.setLayoutY(300); // Y-Koordinate: 400 Pixelad
        AnchorPane SpritePane = (AnchorPane) scene.lookup("#SpritePane");
        SpritePane.getChildren().add(C1);
        spriteAnimationController1 = new SpriteAnimationController(C1,1);
        spriteAnimationController1.initialize();

        ProgressBar hp01 = (ProgressBar) scene.lookup("#hp01");
        ProgressBar hp02 = (ProgressBar) scene.lookup("#hp02");

        PlayerController.player1.setHealthbar(hp01);
        PlayerController.player2.setHealthbar(hp02);

        Canvas C2 = new Canvas(1000, 500);
        C2.setLayoutX(1000); // X-Koordinate: 1200 Pixel
        C2.setLayoutY(270); // Y-Koordinate: 400 Pixel
        SpritePane.getChildren().add(C2);
        spriteAnimationController2 = new SpriteAnimationController(C2,2);
        spriteAnimationController2.initialize();

        timer = new Timer();
        timer.prepareTimer(scene);

        pauseController = new PauseController();
        pauseController.preparePause(scene, timer);

        GMC = new GlobalMoveController(C1,C2,scene,this.spriteAnimationController1, this.spriteAnimationController2,pauseController);
        GMC.start();

        // Hier werden die PropertyChangeListener gesettet
        DamageController DC= new DamageController(GMC,spriteAnimationController1,spriteAnimationController2);
        GMC.addPropertyChangeListener(DC);
        spriteAnimationController1.addPropertyChangeListener(DC);
        spriteAnimationController2.addPropertyChangeListener(DC);

        Label p1name = (Label) scene.lookup("#p1name");
        Label p2name = (Label) scene.lookup("#p2name");

        p1name.textProperty().set(PlayerController.player1.getName());
        p2name.textProperty().set(PlayerController.player2.getName());
    }
    public void chooseMap(int mapNr){
        if(mapNr==1){
            background.getStyleClass().add("map01");
        } else {
            background.getStyleClass().add("map02");
        }
    }
    @FXML
    public void map1() {
        openMap(1);
    }
    @FXML
    public void map2() {
        openMap(2);
    }
    public void endGame() {
        timer.resetTimer();
        openWinMenu();
    }
    @FXML
    private void openWinMenu() {
        System.out.println("WinMenu");
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/WinMenu.fxml", 1920, 1080);
            FXMLLoader loader = factory.getLoader();
            winController = loader.getController();
            winController.setScene(scene);

            factory.showOnStage(scene, Main.startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void exit(){
        Main.exit();
    }

    @FXML
    protected void initialize() {
    }

}