package com.example.travel.adapter.service.orchestration.availability;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile("mock")
public class MockAvailabilityOrchestrator implements AvailabilityOrchestrator {

    @Override
    public String checkAvailability(Map<String, Object> availabilityDetails) {
        System.out.println("--- MOCK AVAILABILITY ORCHESTRATOR --- Input: " + availabilityDetails.toString());
        return "Mock Response: 3 flights available from " + availabilityDetails.get("startPoint") + " to " + availabilityDetails.get("endPoint") + " on " + availabilityDetails.get("date");
    }
}
