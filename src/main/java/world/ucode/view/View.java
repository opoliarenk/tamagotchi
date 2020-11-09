package world.ucode.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import world.ucode.MainTamagotchi;

import java.io.IOException;

public abstract class View {
    private Stage primaryStage = null;
    public Scene scene = null;
    public FXMLLoader loader;
    View(String fxml, Object controller, Stage stage) {
        loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        primaryStage = stage;
    }
    public void setScene() throws IOException {
        Parent root = loader.load();
        scene = new Scene(root);
        if (primaryStage != null) {
            primaryStage.setScene(scene);
        }
    }
    javafx.scene.Scene getScene() {
        return scene;
    }
}
