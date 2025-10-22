package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;
import java.util.Map;

public interface BookingErrorStrategy {
    Map<String, String> handleError(FlightBookingRequest request, ValidationException e);
}