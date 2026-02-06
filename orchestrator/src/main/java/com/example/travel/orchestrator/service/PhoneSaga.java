package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.client.RestPhoneClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;

public class PhoneSaga extends Saga {
    private final RestPhoneClient phoneClient;

    public PhoneSaga(RestDbClient dbClient, CompensationHandler compensation, RestPhoneClient phoneClient) {
        super(dbClient, compensation);
        this.phoneClient = phoneClient;
    }

    @Override
    protected void doExecute(FlightBookingRequest request) {
        phoneClient.create(request.getPhone(), request.getSagaId());
    }
}
