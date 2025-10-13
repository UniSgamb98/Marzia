package com.orodent.marzia.controller;

import com.orodent.marzia.App;
import com.orodent.marzia.models.AppModel;
import com.orodent.marzia.view.ListItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CaptureMeasurementController implements EventHandler<ActionEvent> {
    AppModel model;

    public CaptureMeasurementController(AppModel model){
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        doStuff();
    }

    public void doStuff(){
        IOController ioController = model.getActiveController();
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
