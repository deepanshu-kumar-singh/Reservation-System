package com.example.travel.adapter.service.booking;

import com.example.travel.adapter.dto.FlightBookingResponse;
import com.example.travel.adapter.service.orchestration.OrchestrationService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BookingService {

    private final OrchestrationService orchestrationService;

    public BookingService(OrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    public Map<String, Object> bookFlight(Map<String, Object> flightDetails) {
        FlightBookingResponse bookingResponse = orchestrationService.bookFlight(flightDetails);
        String formattedMessage = String.format("{%s}{%s}{%s}{%s}",
                bookingResponse.getBookingId(),
                bookingResponse.getAirline(),
                bookingResponse.getSeat(),
                bookingResponse.getStatus());

        return Map.of("flow", "FLIGHT_BOOKING", "status", "SUCCESS", "message", formattedMessage, "flightDetails", flightDetails);
    }

    public Map<String, Object> checkAvailability(Map<String, Object> availabilityDetails) {
        String result = orchestrationService.checkAvailability(availabilityDetails);
        return Map.of("flow", "AVAILABILITY", "status", "SUCCESS", "message", result, "availabilityDetails", availabilityDetails);
    }

    public Map<String, Object> processNameQuery(String name) {
        String result = orchestrationService.processNameQuery(name);
        return Map.of("flow", "NAME_QUERY", "status", "SUCCESS", "name", name, "message", result);
    }

    public Map<String, Object> processPhoneNumberQuery(String phoneNumber) {
        String result = orchestrationService.processPhoneNumberQuery(phoneNumber);
        return Map.of("flow", "PHONE_NUMBER_QUERY", "status", "SUCCESS", "phoneNumber", phoneNumber, "message", result);
    }
}
