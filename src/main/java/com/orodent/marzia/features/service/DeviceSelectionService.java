package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppModel;

public class DeviceSelectionService {
    private final AppModel model;

    public DeviceSelectionService(AppModel model) {
        this.model = model;
    }

    public void swapActiveDevice() {
        model.swapActiveController();
    }
}
