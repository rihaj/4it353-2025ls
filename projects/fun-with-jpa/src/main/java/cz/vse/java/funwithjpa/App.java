package cz.vse.java.funwithjpa;

import cz.vse.java.funwithjpa.controller.Controller;
import cz.vse.java.funwithjpa.gui.Gui;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static EntityManagerFactory EMF;

    private Gui gui;

    public static void main(String[] args) {
        LOG.info("Application started.");
        EMF = Persistence.createEntityManagerFactory("punit");

        Application.launch(args);

        EMF.close();
        LOG.info("Application terminated.");
    }

    @Override
    public void start(final Stage stage) throws Exception {
        gui = new Gui(new Controller());

        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(gui.getPane());

        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();
        stage.show();
    }
}
