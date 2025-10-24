package com.example.travel.adapter.service.booking.availability;

import com.example.travel.adapter.service.orchestration.availability.AvailabilityOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AvailabilityService {

    private final AvailabilityOrchestrator orchestrator;

    public AvailabilityService(AvailabilityOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String checkAvailability(Map<String, Object> availabilityDetails) {
        String result = orchestrator.checkAvailability(availabilityDetails);
        return  result;
    }
}
