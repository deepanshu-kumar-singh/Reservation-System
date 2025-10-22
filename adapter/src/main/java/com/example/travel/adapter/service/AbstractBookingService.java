package com.example.travel.adapter.service;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.exception.ValidationException;

import java.util.Map;

public abstract class AbstractBookingService {
    
    public final Map<String, String> bookFlight(FlightBookingRequest request) {
        try {
            validate(request);
            return onSuccess(request);
        } catch (ValidationException e) {
            return onError(request, e);
        }
    }

    protected abstract void validate(FlightBookingRequest request) throws ValidationException;

    protected abstract Map<String, String> onSuccess(FlightBookingRequest request);

    protected abstract Map<String, String> onError(FlightBookingRequest request, ValidationException e);
}