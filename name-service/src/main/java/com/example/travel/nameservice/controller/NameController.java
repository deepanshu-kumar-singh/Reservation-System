package com.example.travel.nameservice.controller;

import com.example.travel.nameservice.entity.NameEntity;
import com.example.travel.nameservice.repository.NameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/names")
public class NameController {

    private final NameRepository repo;
    public NameController(NameRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NameEntity n){
        n = repo.save(n);
        return ResponseEntity.ok(java.util.Map.of("id", n.getId()));
    }

    @PostMapping("/compensate/{sagaId}")
    public ResponseEntity<?> compensate(@PathVariable String sagaId){
        repo.deleteBySagaId(sagaId);
        return ResponseEntity.ok(java.util.Map.of("sagaId", sagaId, "compensated", true));
    }
}
