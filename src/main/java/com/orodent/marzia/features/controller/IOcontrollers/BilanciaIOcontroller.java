package com.orodent.marzia.features.controller.IOcontrollers;

import com.ghgande.j2mod.modbus.io.ModbusTCPTransaction;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersRequest;
import com.ghgande.j2mod.modbus.msg.ReadMultipleRegistersResponse;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;

import java.net.InetAddress;

public class BilanciaIOcontroller extends IOController{
    private TCPMasterConnection conn;
    private final int unitId = 255; // FFh
    private final String ip = "192.168.1.201";
    private final int port = 1800;

    private long lastReconnectAttempt = 0;
    private final long reconnectIntervalMs = 2000; // tenta ogni 2s

    public BilanciaIOcontroller(String ip) throws Exception{
        ensureConnected();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try { close(); } catch (Exception ignored) {}
        }));
    }

    @Override
    public String getMeasurement() {
        double pesoKg = readPesoNetto();   // es: 0.0020
        int decimaliKg = readDecimali();   // es: 4
        double pesoGrammi = pesoKg * 1000.0;        // es: 2.0
        int decimaliGrammi = Math.max(decimaliKg - 2, 0);  // es: 1

        return String.format("%." + decimaliGrammi + "f", pesoGrammi);
    }

    @Override
    public void close() {
        disconnect();
    }

    private synchronized void ensureConnected() throws Exception {
        if (conn != null && conn.isConnected()) return;

        long now = System.currentTimeMillis();
        if (now - lastReconnectAttempt < reconnectIntervalMs) return;

        lastReconnectAttempt = now;

        try {
            if (conn != null) conn.close();
        } catch (Exception ignored) {}

        conn = new TCPMasterConnection(InetAddress.getByName(ip));
        conn.setPort(port);
        conn.connect();
        System.out.println("✅ Bilancia connessa");
    }

    public synchronized void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("🔌 Bilancia disconnessa");
            }
        } catch (Exception ignored) {}
        conn = null;
    }

    private int readRegister() throws Exception {
        ensureConnected();
        ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(1102, 1);
        req.setUnitID(unitId);
        ModbusTCPTransaction trans = new ModbusTCPTransaction(conn);
        trans.setRequest(req);
        trans.execute();
        ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();
        return res.getRegister(0).getValue();
    }

    public int readDecimali() {
        try {
            return readRegister();
        } catch (Exception e) {
            return 0;
        }
    }

    public double readPesoNetto() {
        try {
            ensureConnected();
            ReadMultipleRegistersRequest req = new ReadMultipleRegistersRequest(3, 2);
            req.setUnitID(unitId);

            ModbusTCPTransaction trans = new ModbusTCPTransaction(conn);
            trans.setRequest(req);
            trans.execute();

            ReadMultipleRegistersResponse res = (ReadMultipleRegistersResponse) trans.getResponse();
            int msw = res.getRegister(0).getValue();
            int lsw = res.getRegister(1).getValue();

            int raw = (msw << 16) | (lsw & 0xFFFF);
            int decimali = readDecimali() + 1; // lasciamo così come ti funziona

            return raw / Math.pow(10, decimali);

        } catch (Exception e) {
            // Se errore → proviamo a riconnettere nel prossimo ciclo
            disconnect();
            return Double.NaN;
        }
    }
}
