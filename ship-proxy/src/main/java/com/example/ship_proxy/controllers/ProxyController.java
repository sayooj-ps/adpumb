package com.example.ship_proxy.controllers;

import com.example.ship_proxy.service.ProxyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class ProxyController {

    @GetMapping("/**")
    public ResponseEntity<String> handleProxy(HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace("http://localhost:8080", "http://");
        log.info("Forwarding: {}", url);
        String result = ProxyService.sendRequest(url);
        return ResponseEntity.ok(result);
    }
}
