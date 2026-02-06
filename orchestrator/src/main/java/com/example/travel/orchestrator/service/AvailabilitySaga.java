package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestAvailabilityClient;
import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;

public class AvailabilitySaga extends Saga {
    private final RestAvailabilityClient availabilityClient;

    public AvailabilitySaga(RestDbClient dbClient, CompensationHandler compensation, RestAvailabilityClient availabilityClient) {
        super(dbClient, compensation);
        this.availabilityClient = availabilityClient;
    }

    @Override
    protected void doExecute(FlightBookingRequest request) {
        availabilityClient.reserve(request.getFlightId(), request.getClassOfService(), request.getSagaId());
    }
}
