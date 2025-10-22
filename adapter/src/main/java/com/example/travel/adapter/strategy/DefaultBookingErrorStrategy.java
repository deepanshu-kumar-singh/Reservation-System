package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.of;

@Component
public class DefaultBookingErrorStrategy implements BookingErrorStrategy {
    @Override
    public Map<String, String> handleError(FlightBookingRequest request, ValidationException e) {
        return of("status", "VALIDATION_FAILED", "error", e.getMessage());
    }
}