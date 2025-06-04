package com.orodent.marzia.view;

import com.orodent.marzia.App;
import com.orodent.marzia.controller.CaptureMeasurementController;
import com.orodent.marzia.controller.CopyOnClipBoardController;
import com.orodent.marzia.controller.IOController;
import com.orodent.marzia.controller.ResetController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends BorderPane {

    public View(IOController ioController) {
        this.getStyleClass().add("custom-view");

        // HBox con i ChoiceBox in alto
        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15));
        topBox.setAlignment(Pos.CENTER);

        Button reset = new Button("Reset");
        reset.setOnAction(new ResetController());
        setTop(reset);

        // Bottone centrale
        Button acquisisciButton = new Button("Acquisisci");
        acquisisciButton.setOnAction(new CaptureMeasurementController(ioController));
        BorderPane.setAlignment(acquisisciButton, Pos.CENTER);
        setCenter(acquisisciButton);

        // Bottone inferiore
        Button copyButton = new Button("Copia su appunti");
        copyButton.getStyleClass().add("bottom-button");
        copyButton.setMaxWidth(Double.MAX_VALUE);
        copyButton.setOnAction(new CopyOnClipBoardController());

        VBox bottomBox = new VBox(copyButton);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setAlignment(Pos.CENTER);
        setBottom(bottomBox);

        // ListView a destra
        ListView<ListItem> listView = new ListView<>(App.measurement);
        listView.setPrefWidth(250);
        setRight(listView);
    }
}