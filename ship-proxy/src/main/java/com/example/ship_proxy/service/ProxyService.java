package com.example.ship_proxy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.Socket;

@Slf4j
public class ProxyService {
    private static Socket socket;
    private static BufferedWriter out;
    private static BufferedReader in;
    private static final Object LOCK = new Object();
    private static String host;
    private static int port;

    public static void init(String configHost, int configPort) {
        host = configHost;
        port = configPort;
        connect();
    }

    private static void connect() {
        try {
            socket = new Socket(host, port);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            log.info("Connected to offshore proxy");
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to proxy server: " + e.getMessage(), e);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String sendRequest(String url) {
        synchronized (LOCK) {
            if (socket == null || socket.isClosed() || !socket.isConnected()) {
                connect();
            }
            try {
                out.write(url + "\n");
                out.flush();
                return getResponseString();
            } catch (IOException e) {
                throw new RuntimeException("Failed to connect to server\n" + e.getMessage());
            } catch (Exception e){
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    private static String getResponseString() throws IOException {
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.equals("<<<END>>>")) break;
            response.append(line).append("\n");
        }
        return response.toString();
    }
}
