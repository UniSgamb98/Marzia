package com.orodent.marzia.features.controller;
import java.io.BufferedReader;
import java.io.IOException;

public class KeyenceStreamReader implements AutoCloseable {

    private volatile String ultimaRiga = null;
    private final BufferedReader reader;
    private final Thread thread;
    private volatile boolean running = true; // flag di controllo

    public KeyenceStreamReader(BufferedReader reader) {
        this.reader = reader;
        this.thread = new Thread(() -> {
            String line;
            try {
                while (running && (line = reader.readLine()) != null) {
                    ultimaRiga = line;
                }
            } catch (IOException e) {
                if (running) { // ignora se è dovuto alla chiusura
                    System.err.println("❌ Errore nel thread di lettura: " + e.getMessage());
                }
            }
        }, "KeyenceStreamReader");
        thread.setDaemon(true);
        thread.start();
    }

    public String getUltimaRiga() {
        return ultimaRiga;
    }

    @Override
    public void close() {
        running = false;
        try {
            reader.close(); // chiude lo stream → fa uscire readLine() dal blocco
        } catch (IOException ignored) {}
        try {
            thread.join(500); // aspetta un po' la terminazione
        } catch (InterruptedException ignored) {}
    }
}


