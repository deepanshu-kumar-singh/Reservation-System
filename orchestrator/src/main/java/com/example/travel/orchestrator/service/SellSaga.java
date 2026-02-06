package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.client.RestSellClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;

public class SellSaga extends Saga {
    private final RestSellClient sellClient;

    public SellSaga(RestDbClient dbClient, CompensationHandler compensation, RestSellClient sellClient) {
        super(dbClient, compensation);
        this.sellClient = sellClient;
    }

    @Override
    protected void doExecute(FlightBookingRequest request) {
        sellClient.createSell(request.getSagaId(), request.getNameId(), request.getPhoneId(), request.getReservationId());
    }
}
