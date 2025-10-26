package com.example.travel.adapter.service.orchestration.availability;

import java.util.Map;

public interface AvailabilityOrchestrator {
    String checkAvailability(Map<String, Object> availabilityDetails);
}
