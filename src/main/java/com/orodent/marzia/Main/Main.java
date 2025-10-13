package com.orodent.marzia.Main;

import com.orodent.marzia.controller.CaptureMeasurementController;
import com.orodent.marzia.controller.ChangeMeasurementController;
import com.orodent.marzia.models.AppModel;
import com.orodent.marzia.view.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        AppModel model = new AppModel();

        View view = new View(model);
        Scene scene = new Scene(view, 620, 950);

        // Gestione della pulsante a pedale
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            private long lastEnterTime = 0;
            private static final long DOUBLE_PRESS_THRESHOLD = 300; // ms
            private boolean waitingForDouble = false;

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    long now = System.currentTimeMillis();

                    if (now - lastEnterTime < DOUBLE_PRESS_THRESHOLD) {
                        // 🔁 Doppio INVIO → annulla il singolo
                        waitingForDouble = false;
                        onDoubleEnter();
                    } else {
                        waitingForDouble = true;

                        // ⏳ Se entro X ms non arriva un secondo invio, esegui il singolo
                        new Thread(() -> {
                            try {
                                Thread.sleep(DOUBLE_PRESS_THRESHOLD);
                            } catch (InterruptedException ignored) {}
                            if (waitingForDouble) {
                                waitingForDouble = false;
                                Platform.runLater(this::onSingleEnter);
                            }
                        }).start();
                    }

                    lastEnterTime = now;
                    event.consume();
                }
            }

            private void onSingleEnter() {
                new CaptureMeasurementController(model).doStuff();
                System.out.println("➡️ Singolo INVIO eseguito");
            }

            private void onDoubleEnter() {
                new ChangeMeasurementController(model).doStuff();
                System.out.println("🔁 Doppio INVIO eseguito");
            }
        });

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