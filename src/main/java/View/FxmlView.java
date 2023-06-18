package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlView {

    public FXMLLoader loader;
    public Scene currentScene;

    public FxmlView(){

    }
    public Stage start(Stage stage, String url, String title) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        System.out.println(loader.getLocation());
        currentScene = new Scene(loader.load(), 1920, 1080);
        stage.setTitle(title);
        stage.setScene(currentScene);
        return stage;
        //startStage=stage;
        //stage.show();

    }
    public void load(String url, String title) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        currentScene = new Scene(loader.load(), 1920, 1080);
        System.out.println(loader.getLocation());
        Main.startStage.setTitle(title);
        Main.startStage.setScene(currentScene);
        Main.startStage.show();
    }
    public Scene getScene(){
        return currentScene;
    }
    public void exit(){
        Main.startStage.close();
    }
    public Stage getStartStage(){
        return Main.startStage;
    }
}
