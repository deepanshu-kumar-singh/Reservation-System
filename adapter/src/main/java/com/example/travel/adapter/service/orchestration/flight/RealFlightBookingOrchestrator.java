package com.example.travel.adapter.service.orchestration.flight;

import com.example.travel.adapter.dto.SellResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Profile("!mock")
public class RealFlightBookingOrchestrator implements FlightBookingOrchestrator {

    private final RestTemplate restTemplate;

    public RealFlightBookingOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public SellResponse bookFlight(Map<String, Object> flightDetails) {
        return restTemplate.postForObject("http://flight-booking-service/api/v1/flights", flightDetails, SellResponse.class);
    }
}
