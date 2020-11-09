package world.ucode.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import world.ucode.controller.ControllerGamePlay;

import java.io.IOException;

public class Model {
    double health;
    double happiness;
    double hunger;
    double thirst;
    double cleanliness;
    double gameMode;
    int type;
    String name;
    public Timeline timelineScore;

    public Model(String name, int type) {
        this.type = type;
        this.name = name;
        health = 1;
        happiness = 1;
        hunger = 1;
        thirst = 1;
        cleanliness = 1;
    }

    public Model(String name, double health, double happiness, double hunger, double thirst, double cleanliness, int type) {
        this.type = type;
        this.name = name;
        this.cleanliness = cleanliness;
        this.thirst = thirst;
        this.hunger = hunger;
        this.happiness = happiness;
        this.health = health;
    }

    public int getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public double getHealth() {
        return health;
    }
    public double getHappiness() {
        return happiness;
    }
    public double getHunger() {
        return hunger;
    }
    public double getThirst() {
        return thirst;
    }
    public double getcleanliness() {
        return cleanliness;
    }
    public double getGameMode() {
        return cleanliness;
    }
    public void setHealth(double health) {
        this.health = health;
    }
    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }
    public void setHunger(double hunger) {
        this.hunger = hunger;
    }
    public void setThirst(double thirst) {
        this.thirst = thirst;
    }
    public void setcleanliness(double cleanliness) {
        this.cleanliness = cleanliness;
    }
    public void setGameMode(int mode) {
        if (mode == 0) {
            gameMode = 1;
        } else if (mode == 1) {
            gameMode = 0.7;
        } else {
            gameMode = 0.5;
        }
    }
    public void setType(int type) {
        this.type = type;
    }

    public void startGameLoop() {
        timelineScore = new Timeline();
        timelineScore.setCycleCount(Timeline.INDEFINITE);

        timelineScore.getKeyFrames().add(
                new KeyFrame(Duration.seconds(gameMode), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        health -= 0.001;
                        happiness -= 0.001;
                        hunger -= 0.02;
                        thirst -= 0.03;
                        cleanliness -= 0.02;
                        if (cleanliness < 0.5 || hunger < 0.6 || thirst < 0.7)
                            happiness -= 0.02;
                        if (hunger < 0.5) {
                            health -= 0.06;
                            happiness -= 0.01;
                        }
                        if (thirst < 0.6)
                            health -= 0.06;
                        if (hunger < 0) {
                            try {
                                timelineScore.stop();
                                ControllerGamePlay.switchToGameOverEat();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (health < 0) {
                            try {
                                timelineScore.stop();
                                ControllerGamePlay.switchToGameOver();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }));
    }
}
