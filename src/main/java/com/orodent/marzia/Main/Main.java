package com.orodent.marzia.Main;

import com.orodent.marzia.controller.CaptureMeasurementController;
import com.orodent.marzia.controller.IOController;
import com.orodent.marzia.view.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Apro la socket
        IOController ioController;
        try {
            ioController = new IOController("192.168.1.150");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ioController.write("T1");
        View view = new View(ioController);
        Scene scene = new Scene(view, 520, 300);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                new CaptureMeasurementController(ioController).doStuff();
                event.consume(); // impedisce ai nodi figli di ricevere l'evento, se serve
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