package com.orodent.marzia.features.service.io;

public abstract class IOController {

    public IOController() {}

    public abstract String getMeasurement();

    public abstract void close();

}
