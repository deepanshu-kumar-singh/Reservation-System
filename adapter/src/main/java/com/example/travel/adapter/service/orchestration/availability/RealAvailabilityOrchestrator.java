package com.example.travel.adapter.service.orchestration.availability;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Profile("!mock")
public class RealAvailabilityOrchestrator implements AvailabilityOrchestrator {

    private final RestTemplate restTemplate;

    public RealAvailabilityOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String checkAvailability(Map<String, Object> availabilityDetails) {
        return restTemplate.postForObject("http://availability-service/api/v1/availability", availabilityDetails, String.class);
    }
}
