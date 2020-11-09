package world.ucode.controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import world.ucode.model.Model;
import world.ucode.view.GamePlayView;
import world.ucode.view.MenuView;

public class ControllerNewGame extends Controller {
    public int num = 0;

    ArrayList <String> petScene;

    @FXML
    public TextField characterName;
    public Button menu;
    public ImageView pandaImg;
    public ImageView grizzImg;
    public ImageView iceImg;
    public Label enterCharacterName;
    public Button gameMode;

    ControllerNewGame(Stage stage) {
        super(stage);
        petScene = new ArrayList<String>();
        petScene.add("/world/ucode/pandaPet.fxml");
        petScene.add("/world/ucode/grizzPet.fxml");
        petScene.add("/world/ucode/icePet.fxml");
    }

    public void changeCharacter(int num) {
        if (num == 0) {
            gameMode.setText("Easy");
            pandaImg.setVisible(true);
            grizzImg.setVisible(false);
            iceImg.setVisible(false);
        } else if (num == 1) {
            gameMode.setText("Medium");
            grizzImg.setVisible(true);
            pandaImg.setVisible(false);
            iceImg.setVisible(false);
        } else if (num == 2) {
            gameMode.setText("Hard");
            iceImg.setVisible(true);
            pandaImg.setVisible(false);
            grizzImg.setVisible(false);
        }
    }

    @FXML
    public void right() throws IOException {
        if (++num == 3)
            num = 0;
        changeCharacter(num);
    }

    @FXML
    public void left() throws IOException {
        if (--num == -1)
            num = 2;
        changeCharacter(num);
    }

    @FXML
    public void playBtn() throws IOException {
        playSound();
        if (!characterName.getText().isEmpty()) {
            Model model = new Model(characterName.getText(), num);
            model.setGameMode(num);
            model.setType(num);
            model.startGameLoop();
            model.timelineScore.play();
            GamePlayView gamePlayView = new GamePlayView(petScene.get(num), new ControllerGamePlay(model, primaryStage), primaryStage);
            gamePlayView.setScene();
        } else {
            enterCharacterName.setVisible(true);
        }
    }

    @FXML
    public void switchToMenu() throws IOException {
        playSound();
        MenuView menuView = new MenuView("/world/ucode/gameMenu.fxml", new ControllerMenu(primaryStage), primaryStage);
        menuView.setScene();
    }
}