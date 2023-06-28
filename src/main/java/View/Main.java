package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {


   public static Stage startStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/StartMenu.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1000, 500);
        scene.getRoot().requestFocus();
        stage.setTitle("Martial Hero");
        stage.setScene(scene);
        startStage=stage;
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    public static Stage getStartstage(){
        return startStage;
    }

}