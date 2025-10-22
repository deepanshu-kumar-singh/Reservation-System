package com.example.travel.adapter.strategy;

import com.example.travel.adapter.dto.FlightBookingRequest;
import java.util.Map;

public interface BookingSuccessStrategy {
    Map<String, String> handleSuccess(FlightBookingRequest request);
}