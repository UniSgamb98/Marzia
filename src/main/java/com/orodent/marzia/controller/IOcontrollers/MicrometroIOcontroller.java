package com.orodent.marzia.controller.IOcontrollers;

import com.orodent.marzia.controller.KeyenceStreamReader;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class MicrometroIOcontroller extends IOController{
    private final BufferedWriter writer;
    private final KeyenceStreamReader streamReader;
    private final Socket socket;

    public MicrometroIOcontroller() throws IOException {
        super();
        String ip = "192.168.1.150";
        int port = 8600;

        //Apertura Socket
        socket = new Socket(ip, port);
       // socket = new Socket(InetAddress.getByName(null), 80);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { close(); } catch (Exception ignored) {}
        }));

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        streamReader = new KeyenceStreamReader(reader);
    }

    @Override
    public String getMeasurement(){
        String ret;
        try {
            writer.write("T1" + "\r");
            writer.flush();
            ret = streamReader.getUltimaRiga();
            ret = ret.replace(",", "\t");
            ret = ret.replace(".", ",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    @Override
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
