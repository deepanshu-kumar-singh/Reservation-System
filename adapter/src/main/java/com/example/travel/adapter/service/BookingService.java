package com.example.travel.adapter.service;

import com.example.travel.adapter.dto.FlightBookingRequest;

import java.util.Map;

public interface BookingService {
    Map<String, String> bookFlight(FlightBookingRequest request);
}