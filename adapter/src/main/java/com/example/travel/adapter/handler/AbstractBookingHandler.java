package com.example.travel.adapter.handler;

public abstract class AbstractBookingHandler implements BookingHandler {

    private BookingHandler next;

    protected AbstractBookingHandler() {}

    @Override
    public void setNext(BookingHandler handler) {
        this.next = handler;
    }

    protected String handleNext(String request) {
        if (next != null) {
            return next.handle(request);
        }
        return "ERR-001";
    }
}
