package com.example.travel.orchestrator.controller;

import com.example.travel.orchestrator.dto.FlightBookingRequest;
import com.example.travel.orchestrator.service.SagaCoordinator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga/flight")
public class OrchestratorController {

    private final SagaCoordinator coordinator = new SagaCoordinator();

    @PostMapping("/booking")
    public ResponseEntity<?> booking(@RequestBody FlightBookingRequest req){
        req.setType("booking");
        String sagaId = coordinator.start(req);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId));
    }

    @PostMapping("/availability")
    public ResponseEntity<?> availability(@RequestBody FlightBookingRequest req){
        req.setType("availability");
        String sagaId = coordinator.start(req);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId));
    }

    @PostMapping("/phone")
    public ResponseEntity<?> phone(@RequestBody FlightBookingRequest req){
        req.setType("phone");
        String sagaId = coordinator.start(req);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId));
    }

    @PostMapping("/name")
    public ResponseEntity<?> name(@RequestBody FlightBookingRequest req){
        req.setType("name");
        String sagaId = coordinator.start(req);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId));
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sell(@RequestBody FlightBookingRequest req){
        req.setType("sell");
        String sagaId = coordinator.start(req);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId));
    }
}
