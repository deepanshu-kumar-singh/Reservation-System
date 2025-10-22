package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SagaStartSuccessStrategy implements BookingSuccessStrategy {

    private final RestTemplate restTemplate;
    private final String orchestratorUrl;

    public SagaStartSuccessStrategy(RestTemplate restTemplate, @Value("${orchestrator.service.url}") String orchestratorUrl) {
        this.restTemplate = restTemplate;
        this.orchestratorUrl = orchestratorUrl;
    }


    @Override
    public Map<String, String> handleSuccess(FlightBookingRequest request) {
        restTemplate.postForObject(orchestratorUrl, request, String.class);
        return Map.of("status", "SAGA_STARTED");
    }
}