package com.example.travel.adapter.controller;

import com.example.travel.adapter.dto.BookingRequest;
import com.example.travel.adapter.router.RegexBasedBookingRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final RegexBasedBookingRouter bookingRouter;

    public BookingController(RegexBasedBookingRouter bookingRouter) {
        this.bookingRouter = bookingRouter;
    }

    @PostMapping
    public ResponseEntity<String> book(@RequestBody BookingRequest request) {
        String responseBody = bookingRouter.route(request.getQuery());
        return ResponseEntity.accepted().body(responseBody);
    }
}
