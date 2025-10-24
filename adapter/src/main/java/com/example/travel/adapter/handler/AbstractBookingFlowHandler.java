package com.example.travel.adapter.handler;

import java.util.Map;

public abstract class AbstractBookingFlowHandler implements BookingFlowHandler {

    private BookingFlowHandler next;

    protected AbstractBookingFlowHandler() {
        // Default constructor for handlers that don't need a direct service in the abstract class
    }

    @Override
    public void setNext(BookingFlowHandler handler) {
        this.next = handler;
    }

    protected String handleNext(String request) {
        if (next != null) {
            return next.handle(request);
        }
        return  "FORMAT";
    }
}
