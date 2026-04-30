package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppModel;
import com.orodent.marzia.features.service.ClipboardService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CopyOnClipBoardController implements EventHandler<ActionEvent> {
    private final ClipboardService clipboardService;

    public CopyOnClipBoardController(AppModel model) {
        this.clipboardService = new ClipboardService(model);
    }

    @Override
    public void handle(ActionEvent event) {
        clipboardService.copyMeasurementsToClipboard();
    }
}
