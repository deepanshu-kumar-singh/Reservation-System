package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestDbClient;
import com.example.travel.orchestrator.dto.FlightBookingRequest;
import java.util.UUID;

public abstract class Saga {
    protected final RestDbClient dbClient;
    protected final CompensationHandler compensation;

    public Saga(RestDbClient dbClient, CompensationHandler compensation) {
        this.dbClient = dbClient;
        this.compensation = compensation;
    }

    public final String execute(FlightBookingRequest request) {
        String sagaId = request.getSagaId() == null ? UUID.randomUUID().toString() : request.getSagaId();
        request.setSagaId(sagaId);
        dbClient.createSaga(sagaId, "IN_PROGRESS");
        try {
            doExecute(request);
            dbClient.updateSaga(sagaId, "COMPLETED");
            return sagaId;
        } catch (Exception ex) {
            compensation.compensate(sagaId);
            dbClient.updateSaga(sagaId, "COMPENSATED");
            throw new RuntimeException("Saga failed", ex);
        }
    }

    protected abstract void doExecute(FlightBookingRequest request);
}
