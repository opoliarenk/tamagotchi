package world.ucode.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import world.ucode.*;
import world.ucode.model.Model;
import world.ucode.view.GameOverView;
import world.ucode.view.MenuView;


public class ControllerGamePlay extends Controller {
    @FXML
    Button petName;
    @FXML
    ProgressBar health;
    @FXML
    ProgressBar happiness;
    @FXML
    ProgressBar hunger;
    @FXML
    ProgressBar thirst;
    @FXML
    ProgressBar cleanliness;
    private Timeline timelineScore;

    public static Model model;

    ControllerGamePlay(Model model, Stage stage) {
        super(stage);
        this.model = model;
    }

    @FXML
    private void switchToMenu() throws IOException {
        playSound();
        model.timelineScore.stop();
        timelineScore.stop();
        MainTamagotchi.db.deleteRow(model.getName(), model.getType());
        MainTamagotchi.db.insert(model.getName(), model.getType(), model.getHealth(), model.getHunger(), model.getThirst(), model.getcleanliness(), model.getHappiness());
        MenuView menuView = new MenuView("/world/ucode/gameMenu.fxml", new ControllerMenu(primaryStage), primaryStage);
        menuView.setScene();
    }

    public static void switchToGameOver() throws IOException {
        MainTamagotchi.db.deleteRow(model.getName(), model.getType());
        GameOverView gameOverView = new GameOverView("/world/ucode/gameOver.fxml", new ControllerGameOver(primaryStage), primaryStage);
        gameOverView.setScene();
    }

    public static void switchToGameOverEat() throws IOException {
        MainTamagotchi.db.deleteRow(model.getName(), model.getType());
        GameOverView gameOverView = new GameOverView("/world/ucode/gameOverEat.fxml", new ControllerGameOver(primaryStage), primaryStage);
        gameOverView.setScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        petName.setText(model.getName());
        setProgress();
        startGameLoop();
        timelineScore.play();
        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::exitApplication);
    }

    @FXML
    public void exitApplication(WindowEvent event) {
        MainTamagotchi.db.deleteRow(model.getName(), model.getType());
        MainTamagotchi.db.insert(model.getName(), model.getType(), model.getHealth(), model.getHunger(), model.getThirst(), model.getcleanliness(), model.getHappiness());
    }


    @FXML
    public void setProgress() {
        health.setProgress(model.getHealth());
        cleanliness.setProgress(model.getcleanliness());
        happiness.setProgress(model.getHappiness());
        hunger.setProgress(model.getHunger());
        thirst.setProgress(model.getThirst());
    }

    public void startGameLoop() {
        timelineScore = new Timeline();
        timelineScore.setCycleCount(Timeline.INDEFINITE);

        timelineScore.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setProgress();
                    }
                }));
    }

    @FXML
    private void play() throws IOException {
        model.setHappiness(model.getHappiness() + 0.05);
//        happiness.setProgress(happiness.getProgress() + 0.05);
    }
    @FXML
    private void feed() throws IOException {
        model.setHunger(model.getHunger() + 0.05);
//        hunger.setProgress(hunger.getProgress() + 0.05);
    }
    @FXML
    private void giveWater() throws IOException {
        model.setThirst(model.getThirst() + 0.05);
//        thirst.setProgress(thirst.getProgress() + 0.05);
    }
    @FXML
    private void giveMedicine() throws IOException {
        model.setHealth(model.getHealth() + 0.05);
//        health.setProgress(model.getHealth() + 0.1);
    }
    @FXML
    private void cleanUp() throws IOException {
        model.setcleanliness(model.getcleanliness() + 0.05);
//        cleanliness.setProgress(cleanliness.getProgress() + 0.05);
    }
}
