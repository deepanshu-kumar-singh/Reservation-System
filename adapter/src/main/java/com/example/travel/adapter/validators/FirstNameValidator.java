package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

public class FirstNameValidator implements BookingValidator {
    @Override
    public void validate(FlightBookingRequest request) {
        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("firstName required");
        }
    }
}
