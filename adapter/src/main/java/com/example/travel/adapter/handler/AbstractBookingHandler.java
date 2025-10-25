package com.example.travel.adapter.handler;

import com.example.travel.adapter.error.ErrorResponseFactory;

public abstract class AbstractBookingHandler implements BookingHandler {

    private BookingHandler next;
    protected ErrorResponseFactory errorResponseFactory;

    protected AbstractBookingHandler(ErrorResponseFactory errorResponseFactory) {
        this.errorResponseFactory = errorResponseFactory;
    }

    @Override
    public void setNext(BookingHandler handler) {
        this.next = handler;
    }

    protected String handleNext(String request) {
        if (next != null) {
            return next.handle(request);
        }

        return errorResponseFactory.createErrorResponse("ERR-001");
    }
}
