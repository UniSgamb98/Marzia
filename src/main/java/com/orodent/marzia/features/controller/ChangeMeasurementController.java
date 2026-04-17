package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChangeMeasurementController implements EventHandler<ActionEvent> {
    AppModel model;

    public ChangeMeasurementController(AppModel model){
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event){
        doStuff();
    }

    public void doStuff(){
        model.swapActiveController();
    }
}
