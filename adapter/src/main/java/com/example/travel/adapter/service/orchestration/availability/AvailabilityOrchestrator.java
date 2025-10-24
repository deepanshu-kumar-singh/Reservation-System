package com.example.travel.adapter.service.orchestration.availability;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AvailabilityOrchestrator {

    private final RestTemplate restTemplate;

    public AvailabilityOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String checkAvailability(Map<String, Object> availabilityDetails) {
        return restTemplate.postForObject("http://availability-service/api/v1/availability", availabilityDetails, String.class);
    }
}
