package com.example.travel.orchestrator.service;

import com.example.travel.orchestrator.client.RestAvailabilityClient;
import com.example.travel.orchestrator.client.RestBookingClient;
import com.example.travel.orchestrator.client.RestNameClient;
import com.example.travel.orchestrator.client.RestPhoneClient;

public class CompensationHandler {
    private final RestNameClient nameClient = new RestNameClient();
    private final RestPhoneClient phoneClient = new RestPhoneClient();
    private final RestAvailabilityClient availabilityClient = new RestAvailabilityClient();
    private final RestBookingClient bookingClient = new RestBookingClient();

    public void compensate(String sagaId){
        try { bookingClient.compensate(sagaId); } catch(Exception e) {}
        try { availabilityClient.release(sagaId); } catch(Exception e) {}
        try { phoneClient.compensate(sagaId); } catch(Exception e) {}
        try { nameClient.compensate(sagaId); } catch(Exception e) {}
    }
}
