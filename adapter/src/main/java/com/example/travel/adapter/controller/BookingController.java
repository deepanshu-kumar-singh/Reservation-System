package com.example.travel.adapter.controller;

import com.example.travel.adapter.dto.FlightBookingRequest;
import com.example.travel.adapter.service.AbstractBookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final AbstractBookingService bookingService;

    public BookingController(AbstractBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> book(@Valid @RequestBody FlightBookingRequest request) {
        Map<String, String> responseBody = bookingService.bookFlight(request);
        return ResponseEntity.accepted().body(responseBody);
    }
}
