package com.example.travel.adapter.service.orchestration.flight;

import com.example.travel.adapter.dto.SellResponse;

import java.util.Map;

public interface FlightBookingOrchestrator {
    SellResponse bookFlight(Map<String, Object> flightDetails);
}
