package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlView {

    public FXMLLoader loader;
    private Scene currentScene;
    private Stage currentStage;

    public FxmlView(){

    }
    public Stage start(Stage stage, String url, String title) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        System.out.println(loader.getLocation());
        currentScene = new Scene(loader.load(), 1920, 1080);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(currentScene);
        currentStage = stage;
        System.out.println(stage.getY());
        return currentStage;

    }
    public void load(String url, String title) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        currentScene = new Scene(loader.load(), 1920, 1080);
        System.out.println(loader.getLocation());
        Main.startStage.setResizable(false);
        Main.startStage.setTitle(title);
        Main.startStage.setScene(currentScene);
        Main.startStage.setY(0);
        currentStage = Main.startStage;
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
    public void show(Stage stage){
        stage.show();
    }
    public Stage getCurrentStage(){
        return currentStage;
    }
}
