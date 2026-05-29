package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.SessionService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ResetController implements EventHandler<ActionEvent> {
    private final SessionService sessionService;

    public ResetController(AppContext appContext) {
        this.sessionService = new SessionService(appContext);
    }

    @Override
    public void handle(ActionEvent event) {
        sessionService.resetMeasurements();
    }
}
