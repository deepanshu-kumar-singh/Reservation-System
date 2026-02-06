package com.example.travel.flightbooking.controller;

import com.example.travel.flightbooking.entity.FlightBooking;
import com.example.travel.flightbooking.repository.FlightBookingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class FlightBookingController {

    private final FlightBookingRepository repo;
    public FlightBookingController(FlightBookingRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String,Object> body){
        String sagaId = (String) body.get("sagaId");
        FlightBooking b = new FlightBooking();
        b.setSagaId(sagaId);
        b.setNameId(Long.valueOf(String.valueOf(body.get("nameId"))));
        b.setPhoneId(Long.valueOf(String.valueOf(body.get("phoneId"))));
        b.setReservationId(String.valueOf(body.get("reservationId")));
        b.setStatus("CONFIRMED");
        b = repo.save(b);
        return ResponseEntity.ok(Map.of("bookingId", b.getId()));
    }

    @PostMapping("/compensate/{sagaId}")
    public ResponseEntity<?> compensate(@PathVariable String sagaId){
        repo.deleteBySagaId(sagaId);
        return ResponseEntity.ok(Map.of("sagaId", sagaId, "compensated", true));
    }
}
