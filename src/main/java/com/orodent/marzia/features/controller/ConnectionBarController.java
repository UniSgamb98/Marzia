package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.DeviceConnectionService;
import com.orodent.marzia.features.view.partials.ConnectionBarView;

public class ConnectionBarController {
    private final ConnectionBarView view;
    private final AppContext appContext;
    private final DeviceConnectionService deviceConnectionService;

    public ConnectionBarController(ConnectionBarView view, AppContext appContext) {
        this.view = view;
        this.appContext = appContext;
        this.deviceConnectionService = new DeviceConnectionService(appContext);
    }

    public void initialize() {
        view.getMicrometerTrafficLight().setOnMouseClicked(e -> deviceConnectionService.toggleMicrometerConnection());
        view.getBilanciaTrafficLight().setOnMouseClicked(e -> deviceConnectionService.toggleBilanciaConnection());

        appContext.micrometerIOController.addListener((obs, oldValue, newValue) -> view.setMicrometerConnected(newValue != null));
        appContext.bilanciaIOController.addListener((obs, oldValue, newValue) -> view.setBilanciaConnected(newValue != null));

        view.setMicrometerConnected(appContext.getMicrometerIOController() != null);
        view.setBilanciaConnected(appContext.getBilanciaIOController() != null);
    }
}
