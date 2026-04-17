package com.orodent.marzia.Main;

import com.orodent.marzia.controller.CaptureMeasurementController;
import com.orodent.marzia.controller.ChangeMeasurementController;
import com.orodent.marzia.controller.PedalInputHandler;
import com.orodent.marzia.models.AppModel;
import com.orodent.marzia.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        try {
            System.setErr(new PrintStream("log.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AppModel model = new AppModel();

        View view = new View(model);
        Scene scene = new Scene(view, 620, 950);

        // Gestione della pulsante a pedale
        new PedalInputHandler(scene, new CaptureMeasurementController(model), new ChangeMeasurementController(model));

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/listItem.css")).toExternalForm());
        stage.setTitle("Orodent magic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}