package com.example.travel.phoneservice.controller;

import com.example.travel.phoneservice.entity.PhoneEntity;
import com.example.travel.phoneservice.repository.PhoneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    private final PhoneRepository repo;
    public PhoneController(PhoneRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PhoneEntity p){
        p = repo.save(p);
        return ResponseEntity.ok(java.util.Map.of("id", p.getId()));
    }

    @PostMapping("/compensate/{sagaId}")
    public ResponseEntity<?> compensate(@PathVariable String sagaId){
        repo.deleteBySagaId(sagaId);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId, "compensated", true));
    }
}
