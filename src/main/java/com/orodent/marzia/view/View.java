package com.orodent.marzia.view;

import com.orodent.marzia.controller.CaptureMeasurementController;
import com.orodent.marzia.controller.CopyOnClipBoardController;
import com.orodent.marzia.controller.ResetController;
import com.orodent.marzia.models.AppModel;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class View extends BorderPane {

    public View(AppModel model) {
        this.getStyleClass().add("custom-view");

        // HBox con i ChoiceBox in alto
        HBox topBox = new HBox(10);
        topBox.setPadding(new Insets(15));
        topBox.setAlignment(Pos.CENTER);

        Button reset = new Button("Reset");
        reset.setOnAction(new ResetController(model));
        HBox top = new HBox(reset, new ConnectionBarView(model));
        setTop(top);

        // ListView a centro
        ListView<ListItem> listView = new ListView<>(model.measurement);
        model.measurement.addListener((ListChangeListener<ListItem>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Platform.runLater(() -> {
                        listView.layout(); // forza il layout aggiornato
                        listView.scrollTo(listView.getItems().size() - 1);
                    });
                }
            }
        });
        listView.setPrefWidth(350);
        listView.setMaxWidth(450);
        setCenter(listView);

        // Bottone sinistra
        Button acquisisciButton = new Button("Acquisisci da\nmicrometro");
        acquisisciButton.setOnAction(new CaptureMeasurementController(model));
        BorderPane.setAlignment(acquisisciButton, Pos.CENTER);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/eyeIcon.png")).toExternalForm()));
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        model.activeControllerProp.addListener((obs, oldValue, newValue) ->{
            if(newValue){
                acquisisciButton.setText("Acquisisci da\nbilancia");
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/scaleIcon.png")).toExternalForm()));
            } else {
                acquisisciButton.setText("Acquisisci da\nmicrometro");
                imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/eyeIcon.png")).toExternalForm()));
            }
        });
        VBox centerBox = new VBox(acquisisciButton, imageView);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(15));
        setLeft(centerBox);


        // Bottone inferiore
        Button copyButton = new Button("Copia su appunti");
        copyButton.getStyleClass().add("bottom-button");
        copyButton.setMaxWidth(Double.MAX_VALUE);
        copyButton.setOnAction(new CopyOnClipBoardController(model));

        VBox bottomBox = new VBox(copyButton);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setAlignment(Pos.CENTER);
        setBottom(bottomBox);
    }
}