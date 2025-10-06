package com.orodent.marzia.controller;

import com.orodent.marzia.App;
import com.orodent.marzia.view.ListItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;

public class CaptureMeasurementController implements EventHandler<ActionEvent> {
    IOController ioController;

    public CaptureMeasurementController(IOController ioController){
        this.ioController = ioController;
    }

    @Override
    public void handle(ActionEvent event) {
        doStuff();
    }

    public void doStuff(){
        String response = ioController.write("T1");
        response = response.replace(",", "\t");
        response = response.replace(".", ",");
        System.out.println("Risposta dal controller: " + response);
        ListItem listItem = new ListItem(response);
        listItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
            App.measurement.remove(listItem);
            ListItem.n--;
        });
        App.measurement.add(listItem);

    }
}
