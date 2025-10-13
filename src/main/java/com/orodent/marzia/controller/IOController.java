package com.orodent.marzia.controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class IOController {
    private final BufferedWriter writer;
    private final KeyenceStreamReader streamReader;
    private final Socket socket;

    public IOController(String ip) throws IOException {
        int port = 8600;

        //Apertura Socket
        socket = new Socket(InetAddress.getByName(null), 80);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { close(); } catch (Exception ignored) {}
        }));

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        streamReader = new KeyenceStreamReader(reader);
    }

    public String write(String command){
        String ret;
        try {
            writer.write(command + "\r");
            writer.flush();
            ret = streamReader.getUltimaRiga();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    public void close() {
        System.out.println("Chiusura connessione...");
        try {
            if (writer != null) writer.close();
        } catch (Exception ignored) {}
        try {
            if (streamReader != null) streamReader.close();
        } catch (Exception ignored) {}
        try {
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (Exception ignored) {}
    }
}
