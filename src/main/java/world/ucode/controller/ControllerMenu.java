package world.ucode.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import world.ucode.view.LoadGameView;
import world.ucode.view.NewGameView;

public class ControllerMenu extends Controller {
    public ControllerMenu(Stage stage) {
        super(stage);
    }

    @FXML
    private void switchToNewGame() throws IOException {
        playSound();
        NewGameView newGameView = new NewGameView("/world/ucode/newGame.fxml", new ControllerNewGame(primaryStage), primaryStage);
        newGameView.setScene();
    }

    @FXML
    private void switchToLoadGame() throws IOException {
        playSound();
        LoadGameView loadGameView = new LoadGameView("/world/ucode/gameLoad.fxml", new ControllerGameLoad(primaryStage), primaryStage);
        loadGameView.setScene();
    }

    @FXML
    private void exitGame() {
        playSound();
        System.exit(0);
    }
}
