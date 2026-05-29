package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppContext;
import com.orodent.marzia.features.service.ClipboardService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CopyOnClipBoardController implements EventHandler<ActionEvent> {
    private final ClipboardService clipboardService;

    public CopyOnClipBoardController(AppContext appContext) {
        this.clipboardService = new ClipboardService(appContext);
    }

    @Override
    public void handle(ActionEvent event) {
        clipboardService.copyMeasurementsToClipboard();
    }
}
