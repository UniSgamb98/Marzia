package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.ClipboardService;
import com.orodent.marzia.features.service.DeviceSelectionService;
import com.orodent.marzia.features.service.MeasurementCaptureService;
import com.orodent.marzia.features.service.SessionService;
import com.orodent.marzia.features.view.MeasuresView;
import com.orodent.marzia.features.view.partials.ListItem;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;

public class MeasuresController {
    private final MeasuresView view;
    private final AppContext appContext;
    private final MeasurementCaptureService measurementCaptureService;
    private final DeviceSelectionService deviceSelectionService;
    private final ClipboardService clipboardService;
    private final SessionService sessionService;

    public MeasuresController(MeasuresView view, AppContext appContext) {
        this.view = view;
        this.appContext = appContext;
        this.measurementCaptureService = new MeasurementCaptureService(appContext);
        this.deviceSelectionService = new DeviceSelectionService(appContext);
        this.clipboardService = new ClipboardService(appContext);
        this.sessionService = new SessionService(appContext);
    }

    public void initialize() {
        view.setMeasurements(appContext.measurement);
        view.getResetButton().setOnAction(this::resetMeasurements);
        view.getCaptureButton().setOnAction(this::captureMeasurement);
        view.getCopyButton().setOnAction(this::copyMeasurementsToClipboard);

        appContext.measurement.addListener((ListChangeListener<ListItem>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Platform.runLater(view::scrollToLastMeasurement);
                }
            }
        });

        appContext.activeControllerProp.addListener((obs, oldValue, newValue) -> updateActiveDevice(newValue));
        updateActiveDevice(appContext.activeControllerProp.getValue());
    }

    public void captureMeasurement(ActionEvent event) {
        measurementCaptureService.captureFromActiveDevice();
    }

    public void swapActiveDevice(ActionEvent event) {
        deviceSelectionService.swapActiveDevice();
    }

    private void resetMeasurements(ActionEvent event) {
        sessionService.resetMeasurements();
    }

    private void copyMeasurementsToClipboard(ActionEvent event) {
        clipboardService.copyMeasurementsToClipboard();
    }

    private void updateActiveDevice(boolean bilanciaActive) {
        if (bilanciaActive) {
            view.showBilanciaMode();
        } else {
            view.showMicrometerMode();
        }
    }
}
