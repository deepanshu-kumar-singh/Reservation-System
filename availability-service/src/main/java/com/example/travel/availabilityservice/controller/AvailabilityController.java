package com.example.travel.availabilityservice.controller;

import com.example.travel.availabilityservice.entity.Availability;
import com.example.travel.availabilityservice.repository.AvailabilityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityRepository repo;
    public AvailabilityController(AvailabilityRepository repo){ this.repo = repo; }

    @PostMapping("/reserve")
    @Transactional
    public ResponseEntity<?> reserve(@RequestBody Map<String,Object> body){
        Long flightId = Long.valueOf(String.valueOf(body.get("flightId")));
        String cls = (String) body.get("classOfService");
        String sagaId = (String) body.get("sagaId");
        var opt = repo.findByFlightIdAndClassOfService(flightId, cls);
        if(opt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error","no inventory"));
        var a = opt.get();
        if(a.getSeatsAvailable()<=0) return ResponseEntity.status(409).body(Map.of("error","no seats"));
        a.setSeatsAvailable(a.getSeatsAvailable()-1);
        repo.save(a);
        return ResponseEntity.ok(Map.of("reservationId", sagaId+"-res")); // simple reservation id
    }

    @PostMapping("/release")
    public ResponseEntity<?> release(@RequestBody Map<String,Object> body){
        String sagaId = (String) body.get("sagaId");
        // For simplicity, do nothing more than acknowledge; in prod we'd restore seat
        return ResponseEntity.ok(Map.of("sagaId", sagaId, "released", true));
    }
}
