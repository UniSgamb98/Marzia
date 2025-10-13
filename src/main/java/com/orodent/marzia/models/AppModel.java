package com.orodent.marzia.models;

import com.orodent.marzia.controller.IOController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.IOException;

public class AppModel {
    public final ObjectProperty<IOController> micrometerIOController;
    public final ObjectProperty<IOController> bilanciaIOController;
    public final SimpleBooleanProperty activeControllerProp;

    private boolean activeController = false;

    public AppModel(){
        micrometerIOController = new SimpleObjectProperty<>();
        bilanciaIOController = new SimpleObjectProperty<>();
        activeControllerProp = new SimpleBooleanProperty(false);
        try {
            micrometerIOController.set(new IOController("192.168.1.150"));
        } catch (IOException ignored) {}
        try {
            bilanciaIOController.set(new IOController("192.168.1.151"));
        } catch (IOException ignored) {}
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
