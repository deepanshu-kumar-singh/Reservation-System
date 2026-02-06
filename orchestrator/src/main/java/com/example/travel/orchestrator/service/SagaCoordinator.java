package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.*;
import com.example.travel.orchestrator.dto.FlightBookingRequest;
import java.util.Map;
import java.util.HashMap;

public class SagaCoordinator {
    private final RestNameClient nameClient = new RestNameClient();
    private final RestPhoneClient phoneClient = new RestPhoneClient();
    private final RestAvailabilityClient availabilityClient = new RestAvailabilityClient();
    private final RestBookingClient bookingClient = new RestBookingClient();
    private final RestSellClient sellClient = new RestSellClient();
    private final RestDbClient dbClient = new RestDbClient();
    private final CompensationHandler compensation = new CompensationHandler();
    private final Map<String, SagaFactory> sagaFactories = new HashMap<>();

    public SagaCoordinator() {
        sagaFactories.put("phone", () -> new PhoneSaga(dbClient, compensation, phoneClient));
        sagaFactories.put("name", () -> new NameSaga(dbClient, compensation, nameClient));
        sagaFactories.put("availability", () -> new AvailabilitySaga(dbClient, compensation, availabilityClient));
        sagaFactories.put("booking", () -> new BookingSaga(dbClient, compensation, bookingClient));
        sagaFactories.put("sell", () -> new SellSaga(dbClient, compensation, sellClient));
           }

    public String start(FlightBookingRequest req) {
        Saga saga = getSaga(req.getType());
        return saga.execute(req);
    }

    private Saga getSaga(String type) {
        SagaFactory factory = sagaFactories.get(type);
        return factory.createSaga();
    }
}
