package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

public class LastNameValidator implements BookingValidator {
    @Override
    public void validate(FlightBookingRequest request) {
        if (request.getLastName() == null || request.getLastName().isBlank()) {
            throw new IllegalArgumentException("lastName required");
        }
    }
}
