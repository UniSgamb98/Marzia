package com.orodent.marzia.features.view.partials;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ConnectionBarView extends HBox {
    private final TrafficLightConnectionView micrometerTrafficLight;
    private final TrafficLightConnectionView bilanciaTrafficLight;

    public ConnectionBarView() {
        Label label1 = new Label("Micrometro:");
        Label label2 = new Label("Bilancia:");

        micrometerTrafficLight = new TrafficLightConnectionView();
        bilanciaTrafficLight = new TrafficLightConnectionView();

        this.getChildren().addAll(label1, micrometerTrafficLight, label2, bilanciaTrafficLight);
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(4, 8, 4, 8));
    }

    public TrafficLightConnectionView getMicrometerTrafficLight() {
        return micrometerTrafficLight;
    }

    public TrafficLightConnectionView getBilanciaTrafficLight() {
        return bilanciaTrafficLight;
    }

    public void setMicrometerConnected(boolean connected) {
        micrometerTrafficLight.setConnected(connected);
    }

    public void setBilanciaConnected(boolean connected) {
        bilanciaTrafficLight.setConnected(connected);
    }
}
