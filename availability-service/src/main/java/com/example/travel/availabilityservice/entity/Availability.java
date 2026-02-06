package com.example.travel.availabilityservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "availability")
public class Availability {
    // getters/setters
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long flightId;
    private String classOfService;
    private Integer seatsAvailable;
    public Availability(){}

}
