package world.ucode;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import world.ucode.controller.ControllerMenu;
import world.ucode.view.MenuView;

import java.io.IOException;
import java.sql.*;

/**
 * JavaFX App
 */
public class MainTamagotchi extends Application {
    public static DataBase db = new DataBase();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            db.createDB();
            db.createTable();
            MenuView menuView = new MenuView("/world/ucode/gameMenu.fxml", new ControllerMenu(primaryStage), primaryStage);
            menuView.setScene();
            primaryStage.show();
        }
        catch (Exception e) {
            System.out.println("error");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}