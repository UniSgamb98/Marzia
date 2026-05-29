package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.io.BilanciaIOcontroller;
import com.orodent.marzia.features.service.io.IOController;
import com.orodent.marzia.features.service.io.MicrometroIOcontroller;

import java.io.IOException;

public class DeviceConnectionService {
    private final AppContext appContext;

    public DeviceConnectionService(AppContext appContext) {
        this.appContext = appContext;
    }

    public void toggleMicrometerConnection() {
        IOController micrometer = appContext.getMicrometerIOController();
        if (micrometer != null) {
            micrometer.close();
            appContext.setMicrometerIOController(null);
            return;
        }

        try {
            appContext.setMicrometerIOController(new MicrometroIOcontroller());
        } catch (IOException ignored) {}
    }

    public void toggleBilanciaConnection() {
        IOController bilancia = appContext.getBilanciaIOController();
        if (bilancia != null) {
            bilancia.close();
            appContext.setBilanciaIOController(null);
            return;
        }

        try {
            appContext.setBilanciaIOController(new BilanciaIOcontroller("192.168.1.201"));
        } catch (Exception ignored) {}
    }
}
