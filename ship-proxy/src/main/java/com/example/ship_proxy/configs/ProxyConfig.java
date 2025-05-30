package com.example.ship_proxy.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ProxyConfig {
    @Value("${offshore.proxy.server.host}")
    private String host;

    @Value("${offshore.proxy.server.port}")
    private int port;
}
