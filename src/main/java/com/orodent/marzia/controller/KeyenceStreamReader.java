package com.orodent.marzia.controller;
import java.io.BufferedReader;
import java.io.IOException;

public class KeyenceStreamReader {

    private volatile String ultimaRiga = null;
    private final boolean running = true;

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
}

