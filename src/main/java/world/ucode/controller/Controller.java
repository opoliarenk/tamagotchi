package world.ucode.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {
    public static Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public Controller(Stage stage) {
        primaryStage = stage;
    }

    public void playSound() {
        String soundName = "/Users/opoliarenk/Desktop/tamagotchi/src/main/resources/world/ucode/rrrou-1.wav";
        try
        {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(soundName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
}
