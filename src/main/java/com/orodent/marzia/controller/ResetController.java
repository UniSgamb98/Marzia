package com.orodent.marzia.controller;

import com.orodent.marzia.models.AppModel;
import com.orodent.marzia.view.ListItem;
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
        model.bilanciaCursor = 0;
        model.micrometroCursor = 0;
    }
}
