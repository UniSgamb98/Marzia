package com.orodent.marzia.view;

import com.orodent.marzia.controller.IOcontrollers.BilanciaIOcontroller;
import com.orodent.marzia.controller.IOcontrollers.IOController;
import com.orodent.marzia.controller.IOcontrollers.MicrometroIOcontroller;
import com.orodent.marzia.models.AppModel;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class TrafficLightConnectionView extends StackPane {
    private final Circle light;

    public TrafficLightConnectionView(AppModel model, int IODevice) {
        Circle border = new Circle(6, Color.BLACK);
        light = new Circle(5, Color.RED);
        getChildren().addAll(border, light);

        //Aggiunta Listener per cambio colore del semaforo
        if (IODevice == 1) {
            model.micrometerIOController.addListener((obs, oldValue, newValue) -> {
                if (newValue != null)   switchColor(Color.GREEN);
                else switchColor(Color.RED);
            });
            if (model.getMicrometerIOController() != null)  switchColor(Color.GREEN);
            else switchColor(Color.RED);
        } else if (IODevice == 2) {
            model.bilanciaIOController.addListener((obs, oldValue, newValue) -> {
                if (newValue != null)   switchColor(Color.GREEN);
                else switchColor(Color.RED);
            });
            if (model.getMicrometerIOController() != null)  switchColor(Color.GREEN);
            else switchColor(Color.RED);
        }

        this.setOnMouseClicked(e -> {
            if (IODevice == 1) {
                IOController c = model.getMicrometerIOController();
                if (c != null){     //era acceso
                    c.close();
                    model.setMicrometerIOController(null);
                } else {        //era spento
                    try {
                        model.setMicrometerIOController(new MicrometroIOcontroller());
                    } catch (IOException ignored) {}
                }
            } else if (IODevice == 2) {
                IOController c = model.getBilanciaIOController();
                if (c != null){     //era acceso
                    c.close();
                    model.setBilanciaIOController(null);
                } else {        //era spento
                    try {
                        model.setBilanciaIOController(new BilanciaIOcontroller("192.168.1.201"));
                    } catch (Exception ignored) {}
                }
            }
        });
    }

    private void switchColor(Color color){
        light.setFill(color);
    }
}
