package com.orodent.marzia.view;

import com.orodent.marzia.models.AppModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ConnectionBarView extends HBox {

    public ConnectionBarView(AppModel model) {
        //Label
        Label label1 = new Label("Micrometro:");
        Label label2 = new Label("Bilancia:");

        TrafficLightConnectionView trafficLight1 = new TrafficLightConnectionView(model, 1);
        TrafficLightConnectionView trafficLight2 = new TrafficLightConnectionView(model, 2);

        //LAYOUT
        this.getChildren().addAll(label1, trafficLight1, label2, trafficLight2);
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(4, 8, 4, 8));
    }
}
