package com.example.travel.dbmanager.controller;

import com.example.travel.dbmanager.entity.SagaStatus;
import com.example.travel.dbmanager.repository.SagaStatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saga")
public class SagaController {

    private final SagaStatusRepository repo;
    public SagaController(SagaStatusRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> createOrUpdate(@RequestBody SagaStatus s){
        if(s.getSagaId()==null) return ResponseEntity.badRequest().body(java.util.Map.of("error","sagaId required"));
        var existing = repo.findById(s.getSagaId()).orElse(new SagaStatus(s.getSagaId(), s.getStatus()));
        existing.setStatus(s.getStatus());
        repo.save(existing);
        return ResponseEntity.ok(java.util.Map.of("sagaId", s.getSagaId(), "status", s.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        return repo.findById(id).map(r->ResponseEntity.ok(java.util.Map.of("sagaId", r.getSagaId(), "status", r.getStatus()))).orElse(ResponseEntity.notFound().build());
    }
}
