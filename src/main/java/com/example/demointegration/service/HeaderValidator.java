package com.example.demointegration.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HeaderValidator {

    private final RestTemplate restTemplate;

    public HeaderValidator() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<?> getData() {
        return this.restTemplate.getForEntity("https://meowfacts.herokuapp.com", String.class);
    }
}
