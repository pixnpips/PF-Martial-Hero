package Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import View.Main;
import javafx.scene.Scene;

import java.io.IOException;


public class FxmlController {

    private String url;
    private String title;
    public FXMLLoader loader;
    public Scene scene;

    public FxmlController(){
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


}
