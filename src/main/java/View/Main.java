package View;

import Model.AudioPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

   public static Stage startStage;
   public static AudioPlayer AP;

    @Override
    public void start(Stage stage) throws IOException {
        FxmlViewFactory factory = new FxmlViewFactory();
        try {
            Scene scene = factory.createSceneFromFXML("/fxml/StartMenu.fxml", 1920, 1080);
            startStage = new Stage();
            factory.showOnStage(scene, startStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AP = new AudioPlayer();
        AP.playBackgroundAudio();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getStartstage(){
        return startStage;
    }

    public static void exit(){
        startStage.close();
        AP.stop();
    }

}