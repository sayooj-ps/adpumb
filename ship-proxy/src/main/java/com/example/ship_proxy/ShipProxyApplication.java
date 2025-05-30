package com.example.ship_proxy;

import com.example.ship_proxy.configs.ProxyConfig;
import com.example.ship_proxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShipProxyApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(ShipProxyApplication.class, args);
		ProxyConfig config = context.getBean(com.example.ship_proxy.configs.ProxyConfig.class);
		ProxyService.init(config.getHost(), config.getPort());
	}
}
