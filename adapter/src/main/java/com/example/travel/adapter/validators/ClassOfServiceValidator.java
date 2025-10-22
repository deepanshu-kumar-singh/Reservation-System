package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

public class ClassOfServiceValidator implements BookingValidator {
    @Override
    public void validate(FlightBookingRequest request) {
        if (request.getClassOfService() == null || request.getClassOfService().isBlank()) {
            throw new IllegalArgumentException("classOfService required");
        }
    }
}
