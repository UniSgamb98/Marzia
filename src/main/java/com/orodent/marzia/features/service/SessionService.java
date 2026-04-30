package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppModel;
import com.orodent.marzia.features.view.partials.ListItem;

public class SessionService {
    private final AppModel model;

    public SessionService(AppModel model) {
        this.model = model;
    }

    public void resetMeasurements() {
        model.measurement.clear();
        ListItem.n = 1;
    }
}
