package com.orodent.marzia.controller;

import com.orodent.marzia.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ResetController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        App.measurement.clear();
    }
}
