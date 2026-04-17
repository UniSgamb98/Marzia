package com.orodent.marzia.features.controller;

import com.orodent.marzia.app.AppModel;
import com.orodent.marzia.features.view.partials.ListItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ResetController implements EventHandler<ActionEvent> {
    private final AppModel model;

    public ResetController(AppModel model) {
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        model.measurement.clear();
        ListItem.n = 1;
    }
}
