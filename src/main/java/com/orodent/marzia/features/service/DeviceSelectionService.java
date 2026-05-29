package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppContext;

public class DeviceSelectionService {
    private final AppContext appContext;

    public DeviceSelectionService(AppContext appContext) {
        this.appContext = appContext;
    }

    public void swapActiveDevice() {
        appContext.swapActiveController();
    }
}
