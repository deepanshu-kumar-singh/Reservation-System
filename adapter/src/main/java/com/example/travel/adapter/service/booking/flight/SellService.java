package com.example.travel.adapter.service.booking.flight;

import com.example.travel.adapter.dto.SellResponse;
import com.example.travel.adapter.service.orchestration.flight.FlightBookingOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SellService {

    private final FlightBookingOrchestrator orchestrator;

    public SellService(FlightBookingOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String bookFlight(Map<String, Object> flightDetails) {
        SellResponse bookingResponse = orchestrator.bookFlight(flightDetails);
        return String.format("{%s}{%s}{%s}{%s}",
                bookingResponse.getBookingId(),
                bookingResponse.getAirline(),
                bookingResponse.getSeat(),
                bookingResponse.getStatus());
    }
}
