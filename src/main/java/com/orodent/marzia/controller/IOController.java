package com.orodent.marzia.controller;

import java.io.*;
import java.net.Socket;

public class IOController {
    private final BufferedWriter writer;
    private final KeyenceStreamReader streamReader;
    private final Socket socket;

    public IOController() throws IOException {
        String ip = "192.168.1.150";
        int port = 8600;

        socket = new Socket(ip, port);
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
}
