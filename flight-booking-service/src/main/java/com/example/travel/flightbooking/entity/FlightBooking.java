package com.example.travel.flightbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "flight_booking")
public class FlightBooking {
    // getters/setters
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sagaId;
    private Long nameId;
    private Long phoneId;
    private String reservationId;
    private String status;
    public FlightBooking(){}

}
