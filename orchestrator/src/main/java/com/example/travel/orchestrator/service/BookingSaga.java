package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestBookingClient;
import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;

public class BookingSaga extends Saga {
    private final RestBookingClient bookingClient;

    public BookingSaga(RestDbClient dbClient, CompensationHandler compensation, RestBookingClient bookingClient) {
        super(dbClient, compensation);
        this.bookingClient = bookingClient;
    }

    @Override
    protected void doExecute(FlightBookingRequest request) {
        bookingClient.createBooking(request.getSagaId(), request.getNameId(), request.getPhoneId(), request.getReservationId());
    }
}
