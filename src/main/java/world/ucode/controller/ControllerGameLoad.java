package world.ucode.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import world.ucode.*;
import world.ucode.model.Model;
import world.ucode.view.GamePlayView;
import world.ucode.view.MenuView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControllerGameLoad extends Controller {
    private final ArrayList<String> petScene;
    @FXML
    public TextField textField;
    public String name;
    @FXML
    public Label noSuchPet;

    public Model model;

    ControllerGameLoad(Stage stage){
        super(stage);
        petScene = new ArrayList<String>();
        petScene.add("/world/ucode/pandaPet.fxml");
        petScene.add("/world/ucode/grizzPet.fxml");
        petScene.add("/world/ucode/icePet.fxml");
    }

    private boolean checkIfNameExist(String name) {
        ResultSet rs = MainTamagotchi.db.selectAll();
        try {
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    return true;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @FXML
    private void switchToGamePlay() throws IOException {
        playSound();
        ResultSet rs = MainTamagotchi.db.selectAll();
        String name = textField.getText();
        int y = 0;
        try {
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    model = new Model(name, rs.getDouble("health"), rs.getDouble("happiness"), rs.getDouble("hunger"), rs.getDouble("thirst"), rs.getDouble("cleanliness"), rs.getInt("type"));
                    model.setGameMode((int) rs.getDouble("type"));
                    model.startGameLoop();
                    model.timelineScore.play();
                    y = 1;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        if (y == 1) {
            GamePlayView gamePlayView = new GamePlayView(petScene.get(model.getType()), new ControllerGamePlay(model, primaryStage), primaryStage);
            gamePlayView.setScene();
        } else
            noSuchPet.setVisible(true);
    }

    @FXML
    private void switchToMenu() throws IOException {
        playSound();
        MenuView menuView = new MenuView("/world/ucode/gameMenu.fxml", new ControllerMenu(primaryStage), primaryStage);
        menuView.setScene();
    }
}
