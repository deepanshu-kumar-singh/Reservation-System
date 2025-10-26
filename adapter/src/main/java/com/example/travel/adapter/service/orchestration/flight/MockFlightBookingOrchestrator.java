package com.example.travel.adapter.service.orchestration.flight;

import com.example.travel.adapter.dto.SellResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@Profile("mock")
public class MockFlightBookingOrchestrator implements FlightBookingOrchestrator {

    @Override
    public SellResponse bookFlight(Map<String, Object> flightDetails) {
        System.out.println("--- MOCK FLIGHT BOOKING ORCHESTRATOR --- Input: " + flightDetails.toString());

        SellResponse mockResponse = new SellResponse();
        mockResponse.setBookingId(UUID.randomUUID().toString());
        mockResponse.setStatus("CONFIRMED");
        mockResponse.setAirline((String) flightDetails.get("airlineCode"));
        mockResponse.setSeat("14A");

        return mockResponse;
    }
}
