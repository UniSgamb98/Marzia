package com.orodent.marzia.features.controller.IOcontrollers;

public abstract class IOController {

    public IOController() {}

    public abstract String getMeasurement();

    public abstract void close();

}
