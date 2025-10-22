package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

public class FlightIdValidator implements BookingValidator {
    @Override
    public void validate(FlightBookingRequest request) {
        if (request.getFlightId() == null) {
            throw new IllegalArgumentException("flightId required");
        }
    }
}
