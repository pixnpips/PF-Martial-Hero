package Controller;

import Model.AudioPlayer;
import View.FxmlView;
import View.Main;
import Model.Timer;
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
    private FxmlView View;

    @FXML
    Label p1name;

    @FXML
    Label p2name;


    public MapController(){
    }

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

        chooseMap(mapNr);

        Canvas C1 = new Canvas(1000, 500);
        C1.setLayoutX(000); // X-Koordinate: 1200 Pixel
        C1.setLayoutY(300); // Y-Koordinate: 400 Pixelad
        SpritePane.getChildren().add(C1);
        spriteAnimationController1 = new SpriteAnimationController(C1,1);
        spriteAnimationController1.initialize();

        PlayerController.player1.setHealthbar(hp01);
        PlayerController.player2.setHealthbar(hp02);

        Canvas C2 = new Canvas(1000, 500);
        C2.setLayoutX(1000); // X-Koordinate: 1200 Pixel
        C2.setLayoutY(270); // Y-Koordinate: 400 Pixel
        SpritePane.getChildren().add(C2);
        spriteAnimationController2 = new SpriteAnimationController(C2,2);
        spriteAnimationController2.initialize();

//      GMC.setx_N1(10);
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

        p1name.textProperty().set(PlayerController.player1.getName());
        p2name.textProperty().set(PlayerController.player2.getName());

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
    public void endGame() throws IOException {
        timer.resetTimer();
        openWinMenu();
    }
    @FXML
    private void openWinMenu() throws IOException {
        System.out.println("WinMenu");
//        View = new FxmlView();
//        View.load("/fxml/WinMenu.fxml", "WinMenu");
        FxmlView.setScenefromXML("/fxml/WinMenu.fxml");
        WinController WC = FxmlView.loader.getController();
        WC.initialize(FxmlView.getScene());
    }

    @FXML
    public void exit(){
        Main.exit();
    }

    @FXML
    protected void initialize() {
    }

}