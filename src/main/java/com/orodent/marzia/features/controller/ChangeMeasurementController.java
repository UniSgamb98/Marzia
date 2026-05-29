package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.DeviceSelectionService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ChangeMeasurementController implements EventHandler<ActionEvent> {
    private final DeviceSelectionService deviceSelectionService;

    public ChangeMeasurementController(AppContext appContext){
        this.deviceSelectionService = new DeviceSelectionService(appContext);
    }

    @Override
    public void handle(ActionEvent event){
        deviceSelectionService.swapActiveDevice();
    }
}
