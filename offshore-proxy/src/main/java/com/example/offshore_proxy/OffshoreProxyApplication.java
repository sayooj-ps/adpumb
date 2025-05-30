package com.example.offshore_proxy;

import com.example.offshore_proxy.handlers.ProxyHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class OffshoreProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OffshoreProxyApplication.class, args);

		ExecutorService executor = Executors.newFixedThreadPool(10);
		new Thread(() -> {
			try (ServerSocket serverSocket = new ServerSocket(9090)) {
				System.out.println("Proxy Server started on port 9090");
				while (true) {
					Socket socket = serverSocket.accept();
					executor.submit(new ProxyHandler(socket));
				}
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}).start();
	}
}
