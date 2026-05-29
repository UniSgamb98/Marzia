package com.orodent.marzia.features.view;

import com.orodent.marzia.features.view.partials.ConnectionBarView;
import com.orodent.marzia.features.view.partials.ListItem;
import javafx.collections.ObservableList;
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

public class MeasuresView extends BorderPane {
    private final Button resetButton;
    private final Button captureButton;
    private final Button copyButton;
    private final ListView<ListItem> measurementsListView;
    private final ImageView deviceImageView;
    private final ConnectionBarView connectionBarView;

    public MeasuresView() {
        this.getStyleClass().add("custom-view");

        resetButton = new Button("Reset");
        connectionBarView = new ConnectionBarView();
        HBox top = new HBox(resetButton, connectionBarView);
        setTop(top);

        measurementsListView = new ListView<>();
        measurementsListView.setPrefWidth(350);
        measurementsListView.setMaxWidth(450);
        setCenter(measurementsListView);

        captureButton = new Button();
        BorderPane.setAlignment(captureButton, Pos.CENTER);

        deviceImageView = new ImageView();
        deviceImageView.setFitWidth(120);
        deviceImageView.setPreserveRatio(true);
        deviceImageView.setSmooth(true);
        showMicrometerMode();

        VBox centerBox = new VBox(captureButton, deviceImageView);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(15));
        setLeft(centerBox);

        copyButton = new Button("Copia su appunti");
        copyButton.getStyleClass().add("bottom-button");
        copyButton.setMaxWidth(Double.MAX_VALUE);

        VBox bottomBox = new VBox(copyButton);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setAlignment(Pos.CENTER);
        setBottom(bottomBox);
    }

    public ConnectionBarView getConnectionBarView() {
        return connectionBarView;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getCaptureButton() {
        return captureButton;
    }

    public Button getCopyButton() {
        return copyButton;
    }

    public void setMeasurements(ObservableList<ListItem> measurements) {
        measurementsListView.setItems(measurements);
    }

    public void scrollToLastMeasurement() {
        measurementsListView.layout();
        measurementsListView.scrollTo(measurementsListView.getItems().size() - 1);
    }

    public void showMicrometerMode() {
        captureButton.setText("Acquisisci da\nmicrometro");
        deviceImageView.setImage(loadImage("/images/eyeIcon.png"));
    }

    public void showBilanciaMode() {
        captureButton.setText("Acquisisci da\nbilancia");
        deviceImageView.setImage(loadImage("/images/scaleIcon.png"));
    }

    private Image loadImage(String path) {
        return new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
    }
}
