package com.example.offshore_proxy.handlers;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

@Slf4j
public class ProxyHandler implements Runnable {
    private final Socket socket;

    public ProxyHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream()) {
            log.info("Reached offshore server");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            while (true) {
                String url = reader.readLine();
                if (url == null) break;
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = responseReader.readLine()) != null) {
                    writer.write(line + "\n");
                }
                writer.write("<<<END>>>\n");
                writer.flush();
                log.info("Forwarded from offshore server");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
