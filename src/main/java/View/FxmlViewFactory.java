package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlViewFactory {
    private FXMLLoader loader;

    public FxmlViewFactory() {
        loader = new FXMLLoader();
    }

    public Scene createSceneFromFXML(String url, int width, int height) throws IOException {
        loader.setLocation(Main.class.getResource(url));
        System.out.println(loader.getLocation());
        Scene scene = new Scene(loader.load(), width, height);
        return scene;
    }

    public void showOnStage(Scene scene, Stage stage) {
        stage.setScene(scene);
        stage.hide();
        stage.show();
    }

    public FXMLLoader getLoader() {
        return loader;
    }
}
