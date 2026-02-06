package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.client.RestNameClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;

public class NameSaga extends Saga {
    private final RestNameClient nameClient;

    public NameSaga(RestDbClient dbClient, CompensationHandler compensation, RestNameClient nameClient) {
        super(dbClient, compensation);
        this.nameClient = nameClient;
    }

    @Override
    protected void doExecute(FlightBookingRequest request) {
        nameClient.create(request.getFirstName(), request.getLastName(), request.getSagaId());
    }
}
