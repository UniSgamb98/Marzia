package com.orodent.marzia.features.controller;

import com.orodent.marzia.features.controller.IOcontrollers.IOController;
import com.orodent.marzia.app.AppModel;
import com.orodent.marzia.features.view.partials.ListItem;
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
        String response = ioController.getMeasurement();

        if (model.activeControllerProp.getValue()) {  //false == micrometer   adding from bilancia
            boolean notFound = true;
            for (ListItem i : model.measurement){
                if (i.getBilanciaValue().isEmpty()){
                    notFound = false;
                    i.setBilanciaText(response);
                    break;
                }
            }
            if (notFound){
                ListItem newItem = new ListItem(response, model.activeControllerProp.getValue());
                newItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
                    model.measurement.remove(newItem);
                    ListItem.n--;
                });
                model.measurement.add(newItem);
            }
        } else {    //adding from micrometer
            boolean notFound = true;
            for (ListItem i : model.measurement){
                if (i.getMicrometerValue().isEmpty()){
                    notFound = false;
                    i.setMicrometerText(response);
                    break;
                }
            }
            if (notFound){
                ListItem newItem = new ListItem(response, model.activeControllerProp.getValue());
                newItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
                    model.measurement.remove(newItem);
                    ListItem.n--;
                });
                model.measurement.add(newItem);
            }
        }
    }
}
