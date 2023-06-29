package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlView {

    private static Scene currentScene;
    private static Stage currentStage;
    public static FXMLLoader loader;

    public FxmlView(){
    }
    public static void setScenefromXML(String url) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        System.out.println(loader.getLocation());
        Scene scene = new Scene(loader.load(), 1920, 1080);
        currentScene = scene;
        Main.startStage.setResizable(false);
        Main.startStage.setScene(scene);
        Main.startStage.hide();
        Main.startStage.show();
    }

    public static Scene getScene(){
        return currentScene;
    }
    public void show(Stage stage){
        stage.show();
    }
    public Stage getCurrentStage(){
        return currentStage;
    }
}
