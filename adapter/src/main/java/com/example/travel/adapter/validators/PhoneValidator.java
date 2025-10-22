package com.example.travel.adapter.validators;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.BookingValidator;

public class PhoneValidator implements BookingValidator {
    @Override
    public void validate(FlightBookingRequest request) {
        if (request.getPhone() == null || !request.getPhone().matches("\\+?\\d{10,13}")) {
            throw new IllegalArgumentException("invalid phone");
        }
    }
}
