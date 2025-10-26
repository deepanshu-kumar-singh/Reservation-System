package com.example.travel.adapter.controller;

import com.example.travel.adapter.dto.BookingRequest;
import com.example.travel.adapter.router.Router;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class Controller {

    private final Router router;

    public Controller(Router router) {
        this.router = router;
    }

    @PostMapping
    public ResponseEntity<String> cook(@RequestBody BookingRequest request) {
        String responseBody = router.route(request.getQuery());
        return ResponseEntity.accepted().body(responseBody);
    }
}
