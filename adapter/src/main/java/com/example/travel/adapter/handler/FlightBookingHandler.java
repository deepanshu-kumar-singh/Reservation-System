package com.example.travel.adapter.handler;

import com.example.travel.adapter.dto.FlightBookingRequestParser;
import com.example.travel.adapter.service.booking.flight.FlightBookingService;
import com.example.travel.adapter.validators.flightbooking.FlightBookingValidator;

import java.util.List;
import java.util.Map;

public class FlightBookingHandler extends AbstractBookingFlowHandler {

    private final FlightBookingRequestParser parser;
    private final FlightBookingValidator validator;
    private final FlightBookingService flightBookingService;

    public FlightBookingHandler(FlightBookingService flightBookingService, FlightBookingRequestParser parser, FlightBookingValidator validator) {
        this.flightBookingService = flightBookingService;
        this.parser = parser;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        if (parser.matches(request)) {
            Map<String, Object> flightDetails = parser.parse(request);
            List<String> errors = validator.validate(flightDetails);
            if (errors.isEmpty()) {
                return flightBookingService.bookFlight(flightDetails);
            }
            return "VALIDATION FAILED";
        }
        return handleNext(request);
    }
}
