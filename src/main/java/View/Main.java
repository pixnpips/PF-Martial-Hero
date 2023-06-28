package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

   public static Stage startStage;
   public static Stage initStage;
   public FxmlView View;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/StartMenu.fxml"));
        System.out.println(fxmlLoader.getLocation());
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Hello!");
        stage.setResizable(false);
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

    public static void exit(){
        startStage.close();
    }

    public void restart() throws IOException {
//        View = new FxmlView();
//        View.load("/fxml/StartMenu.fxml", "Martial Hero");
        FxmlView.setScenefromXML("/fxml/StartMenu.fxml");
    }

}