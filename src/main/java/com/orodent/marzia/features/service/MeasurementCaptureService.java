package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.io.IOController;
import com.orodent.marzia.features.view.partials.ListItem;

public class MeasurementCaptureService {
    private final AppContext appContext;

    public MeasurementCaptureService(AppContext appContext) {
        this.appContext = appContext;
    }

    public void captureFromActiveDevice() {
        IOController ioController = appContext.getActiveController();
        if (ioController == null) {
            throw new IllegalStateException("Nessun dispositivo attivo disponibile");
        }

        String response = ioController.getMeasurement();
        if (response == null) {
            response = "";
        }

        if (appContext.activeControllerProp.getValue()) {
            addBilanciaMeasurement(response);
        } else {
            addMicrometerMeasurement(response);
        }
    }

    private void addBilanciaMeasurement(String response) {
        for (ListItem item : appContext.measurement) {
            if (item.getBilanciaValue().isEmpty()) {
                item.setBilanciaText(response);
                return;
            }
        }

        addNewMeasurement(response, true);
    }

    private void addMicrometerMeasurement(String response) {
        for (ListItem item : appContext.measurement) {
            if (item.getMicrometerValue().isEmpty()) {
                item.setMicrometerText(response);
                return;
            }
        }

        addNewMeasurement(response, false);
    }

    private void addNewMeasurement(String response, boolean isBilancia) {
        ListItem newItem = new ListItem(response, isBilancia);
        newItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
            appContext.measurement.remove(newItem);
            ListItem.n--;
        });
        appContext.measurement.add(newItem);
    }
}
