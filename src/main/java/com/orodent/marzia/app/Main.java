package com.orodent.marzia.app;

import com.orodent.marzia.features.controller.MeasuresController;
import com.orodent.marzia.features.controller.PedalInputHandler;
import com.orodent.marzia.features.view.MeasuresView;
import com.orodent.marzia.features.view.partials.ConnectionBarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        try {
            System.setErr(new PrintStream("log.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        AppContext appContext = new AppContext();
        ConnectionBarView connectionBarView = new ConnectionBarView(appContext);
        MeasuresView measuresView = new MeasuresView(connectionBarView);
        MeasuresController measuresController = new MeasuresController(measuresView, appContext);
        measuresController.initialize();

        Scene scene = new Scene(measuresView, 620, 950);

        // Gestione della pulsante a pedale
        new PedalInputHandler(scene, measuresController::captureMeasurement, measuresController::swapActiveDevice);

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
