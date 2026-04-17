package com.orodent.marzia.models;

import com.orodent.marzia.controller.IOcontrollers.BilanciaIOcontroller;
import com.orodent.marzia.controller.IOcontrollers.IOController;
import com.orodent.marzia.controller.IOcontrollers.MicrometroIOcontroller;
import com.orodent.marzia.view.ListItem;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class AppModel {
    public final ObjectProperty<IOController> micrometerIOController;
    public final ObjectProperty<IOController> bilanciaIOController;
    public final SimpleBooleanProperty activeControllerProp;
    public int micrometroCursor = 0;
    public int bilanciaCursor = 0;
    public final ObservableList<ListItem> measurement = FXCollections.observableArrayList();

    private boolean activeController = false;

    public AppModel(){
        micrometerIOController = new SimpleObjectProperty<>();
        bilanciaIOController = new SimpleObjectProperty<>();
        activeControllerProp = new SimpleBooleanProperty(false);
        try {
            micrometerIOController.set(new MicrometroIOcontroller());
        } catch (IOException ignored) {}
        try {
            bilanciaIOController.set(new BilanciaIOcontroller("192.168.1.201"));
        } catch (Exception ignored) {}
    }

    public IOController getBilanciaIOController() {
        return bilanciaIOController.get();
    }

    public IOController getMicrometerIOController() {
        return micrometerIOController.get();
    }

    public void setBilanciaIOController(IOController bilanciaIOController) {
        this.bilanciaIOController.set(bilanciaIOController);
    }

    public void setMicrometerIOController(IOController micrometerIOController) {
        this.micrometerIOController.set(micrometerIOController);
    }

    public void swapActiveController(){
        activeController = !activeController;
        activeControllerProp.set(activeController);
    }

    public IOController getActiveController(){
        if (activeController)   return bilanciaIOController.get();
        else return micrometerIOController.get();
    }
}
