package com.example.travel.flightbooking.repository;

import com.example.travel.flightbooking.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking, Long> {
    void deleteBySagaId(String sagaId);
}
