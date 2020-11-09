package world.ucode.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import world.ucode.view.MenuView;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class ControllerGameOver extends Controller {
    ControllerGameOver(Stage stage) {
        super(stage);
    }

    @FXML
    public void switchToMenu() throws IOException {
        playSound();
        MenuView menuView = new MenuView("/world/ucode/gameMenu.fxml", new ControllerMenu(primaryStage), primaryStage);
        menuView.setScene();
    }

}
