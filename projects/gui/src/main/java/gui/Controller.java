package gui;

import javafx.scene.Scene;

public class Controller {
    private final Gui gui;

    public Controller() {
        gui = new Gui();

        gui.btnStart.setOnAction(event -> startCalculation());
        gui.btnStop.setOnAction(event -> stopCalculation());
    }

    private void startCalculation() {
        System.out.println("Start clicked.");
    }

    private void stopCalculation() {
        System.out.println("Stop clicked.");
    }

    public Scene getScene() {
        return gui.scnScene;
    }
}
