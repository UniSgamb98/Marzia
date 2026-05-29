package com.orodent.marzia.features.service;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.view.partials.ListItem;

public class SessionService {
    private final AppContext appContext;

    public SessionService(AppContext appContext) {
        this.appContext = appContext;
    }

    public void resetMeasurements() {
        appContext.measurement.clear();
        ListItem.n = 1;
    }
}
