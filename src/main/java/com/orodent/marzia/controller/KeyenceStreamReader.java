package com.orodent.marzia.controller;
import java.io.BufferedReader;
import java.io.IOException;

public class KeyenceStreamReader {

    private volatile String ultimaRiga = null;
    private volatile boolean running = true;

    public KeyenceStreamReader(BufferedReader reader) {
        Thread thread = new Thread(() -> {
            String line;
            try {
                while (running && (line = reader.readLine()) != null) {
                    ultimaRiga = line; // Tiene solo l'ultima riga
                }
            } catch (IOException e) {
                System.err.println("❌ Errore nel thread di lettura: " + e.getMessage());
            }
        }, "KeyenceStreamReader");
        thread.setDaemon(true); // Il thread non blocca l'applicazione alla chiusura
        thread.start();
    }

    public String getUltimaRiga() {
        return ultimaRiga;
    }

    public double[] getUltimaMisuraDouble() {
        if (ultimaRiga == null || !ultimaRiga.contains(",")) return null;
        try {
            String[] parts = ultimaRiga.split(",");
            double[] valori = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                valori[i] = Double.parseDouble(parts[i]);
            }
            return valori;
        } catch (Exception e) {
            return null; // Parsing fallito
        }
    }

    public void stop() {
        running = false;
    }
}

