package com.orodent.marzia.features.view.partials;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrafficLightConnectionView extends StackPane {
    private final Circle light;

    public TrafficLightConnectionView() {
        Circle border = new Circle(6, Color.BLACK);
        light = new Circle(5, Color.RED);
        getChildren().addAll(border, light);
    }

    public void setConnected(boolean connected) {
        if (connected) {
            switchColor(Color.GREEN);
        } else {
            switchColor(Color.RED);
        }
    }

    private void switchColor(Color color){
        light.setFill(color);
    }
}
