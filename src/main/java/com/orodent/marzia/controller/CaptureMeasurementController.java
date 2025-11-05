package com.orodent.marzia.controller;

import com.orodent.marzia.controller.IOcontrollers.IOController;
import com.orodent.marzia.models.AppModel;
import com.orodent.marzia.view.ListItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CaptureMeasurementController implements EventHandler<ActionEvent> {
    AppModel model;

    public CaptureMeasurementController(AppModel model){
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        doStuff();
    }

    public void doStuff(){
        IOController ioController = model.getActiveController();
        String response = ioController.getMeasurement();

        if (model.activeControllerProp.getValue()) {  //false == micrometer   adding from bilancia
            model.bilanciaCursor++;
            if (ListItem.n <= model.bilanciaCursor){
                //aggiungo un list item
                ListItem listItem = new ListItem(response, model.activeControllerProp.getValue());
                listItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
                    model.measurement.remove(listItem);
                    ListItem.n--;
                    model.measurement.get(ListItem.n-2).enable();
                    if (model.bilanciaCursor > model.micrometroCursor)
                        model.bilanciaCursor--;
                    else if (model.bilanciaCursor < model.micrometroCursor)
                        model.micrometroCursor--;
                    else {
                        model.micrometroCursor--;
                        model.bilanciaCursor--;
                    }
                });
                if (ListItem.n > 2)
                    model.measurement.get(ListItem.n-3).disable();
                model.measurement.add(listItem);
            } else{
                //mi collego a un item esistente
                ListItem itemToChange = model.measurement.get(model.bilanciaCursor-1);
                itemToChange.setBilanciaText(response);
            }

        } else {    //adding from micrometer
            model.micrometroCursor++;
            if (ListItem.n <= model.micrometroCursor){
                //aggiungo un list item
                ListItem listItem = new ListItem(response, model.activeControllerProp.getValue());
                listItem.toBeRemoved.addListener((obs, oldVal, newVal) -> {
                    model.measurement.remove(listItem);
                    ListItem.n--;
                    model.measurement.get(ListItem.n-2).enable();
                    if (model.bilanciaCursor > model.micrometroCursor)
                        model.bilanciaCursor--;
                    else if (model.bilanciaCursor < model.micrometroCursor)
                        model.micrometroCursor--;
                    else {
                        model.micrometroCursor--;
                        model.bilanciaCursor--;
                    }
                });
                if (ListItem.n > 2)
                    model.measurement.get(ListItem.n-3).disable();
                model.measurement.add(listItem);
            } else{
                //mi collego a un item esistente
                ListItem itemToChange = model.measurement.get(model.micrometroCursor-1);
                itemToChange.setMicrometerText(response);
            }
        }
    }
}
