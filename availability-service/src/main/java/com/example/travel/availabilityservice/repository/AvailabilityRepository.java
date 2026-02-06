package com.example.travel.availabilityservice.repository;

import com.example.travel.availabilityservice.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Optional<Availability> findByFlightIdAndClassOfService(Long flightId, String classOfService);
}
