package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

   public static Stage startStage;
   public FxmlView View;

    @Override
    public void start(Stage stage) throws IOException {
        View = new FxmlView();
        startStage = View.start(stage, "/fxml/StartMenu.fxml", "Martial Hero");
        startStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
    public static Stage getStartstage(){
        return startStage;
    }

    public static void exit(){
        startStage.close();
    }

}