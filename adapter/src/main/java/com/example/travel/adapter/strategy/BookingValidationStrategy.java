package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;

public interface BookingValidationStrategy {
    void validate(FlightBookingRequest request) throws ValidationException;
}