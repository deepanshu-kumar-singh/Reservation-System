package com.example.travel.adapter.service;

import com.example.travel.adapter.dto.FlightBookingRequest;

public interface BookingValidator {
    void validate(FlightBookingRequest request);
}
