package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Gui {
    Scene scnScene;

    ProgressBar prbProgress;

    TextArea txaResult;

    Button btnStart;
    Button btnStop;

    public Gui() {
        initGui();
    }

    private void initGui() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(0, 10, 10, 10));
        vbox.setSpacing(5);

        createHeading("Progress", vbox);
        prbProgress = new ProgressBar(0.5);
        prbProgress.setPrefWidth(310);
        vbox.getChildren().add(prbProgress);

        createHeading("Result", vbox);
        txaResult = new TextArea();
        txaResult.setEditable(false);
        txaResult.setPrefHeight(100);
        txaResult.setPrefWidth(310);
        vbox.getChildren().add(txaResult);

        createHeading("Actions", vbox);
        btnStart = new Button("Start calculation");
        btnStart.setPrefWidth(150);
        btnStop = new Button("Stop calculation");
        btnStop.setPrefWidth(150);
        btnStop.setDisable(true);
        vbox.getChildren().add(new HBox(10, btnStart, btnStop));

        scnScene = new Scene(vbox);
    }

    private void createHeading(String caption, Pane pane) {
        Label label = new Label(caption);
        label.setFont(new Font("Verdana", 15));
        label.setPadding(new Insets(10, 0, 0, 0));

        pane.getChildren().add(label);
    }
}
