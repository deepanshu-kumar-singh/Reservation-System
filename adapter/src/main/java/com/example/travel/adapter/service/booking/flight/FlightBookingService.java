package com.example.travel.adapter.service.booking.flight;

import com.example.travel.adapter.dto.FlightBookingResponse;
import com.example.travel.adapter.service.orchestration.flight.FlightBookingOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FlightBookingService {

    private final FlightBookingOrchestrator orchestrator;

    public FlightBookingService(FlightBookingOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String bookFlight(Map<String, Object> flightDetails) {
        FlightBookingResponse bookingResponse = orchestrator.bookFlight(flightDetails);
        String formattedMessage = String.format("{%s}{%s}{%s}{%s}",
                bookingResponse.getBookingId(),
                bookingResponse.getAirline(),
                bookingResponse.getSeat(),
                bookingResponse.getStatus());

        return formattedMessage;
    }
}
