package com.example.travel.adapter.service.orchestration.flight;

import com.example.travel.adapter.dto.FlightBookingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FlightBookingOrchestrator {

    private final RestTemplate restTemplate;

    public FlightBookingOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FlightBookingResponse bookFlight(Map<String, Object> flightDetails) {
        return restTemplate.postForObject("http://flight-booking-service/api/v1/flights", flightDetails, FlightBookingResponse.class);
    }
}
