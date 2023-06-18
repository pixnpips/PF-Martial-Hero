package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlView {

    private String url;
    private String title;
    public FXMLLoader loader;
    public Scene scene;

    public FxmlView(){

    }
    public void load(String url, String title) throws IOException {
        loader = new FXMLLoader(Main.class.getResource(url));
        scene = new Scene(loader.load(), 1920, 1080);
        System.out.println(loader.getLocation());
        Main.startStage.setTitle(title);
        Main.startStage.setScene(scene);
        Main.startStage.show();
    }
    public Scene getScene(){
        return scene;
    }
    public void exit(){
        Main.startStage.close();
    }
    public Stage getStartStage(){
        return Main.startStage;
    }
}
