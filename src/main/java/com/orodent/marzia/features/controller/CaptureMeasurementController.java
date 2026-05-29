package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.MeasurementCaptureService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CaptureMeasurementController implements EventHandler<ActionEvent> {
    private final MeasurementCaptureService measurementCaptureService;

    public CaptureMeasurementController(AppContext appContext){
        this.measurementCaptureService = new MeasurementCaptureService(appContext);
    }

    @Override
    public void handle(ActionEvent event) {
        measurementCaptureService.captureFromActiveDevice();
    }
}
