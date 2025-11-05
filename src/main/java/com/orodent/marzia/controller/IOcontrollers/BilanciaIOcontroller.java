package com.orodent.marzia.controller.IOcontrollers;

import com.orodent.marzia.controller.BilanciaMCT1302;

public class BilanciaIOcontroller extends IOController{
    private BilanciaMCT1302 bilancia;

    public BilanciaIOcontroller(String ip){
        bilancia = new BilanciaMCT1302();
    }

    @Override
    public String getMeasurement() {
        double pesoKg = bilancia.readPesoNetto();   // es: 0.0020
        int decimaliKg = bilancia.readDecimali();   // es: 4
        double pesoGrammi = pesoKg * 1000.0;        // es: 2.0
        int decimaliGrammi = Math.max(decimaliKg - 2, 0);  // es: 1

        return String.format("%." + decimaliGrammi + "f", pesoGrammi);
    }

    @Override
    public void close() {

    }
}
