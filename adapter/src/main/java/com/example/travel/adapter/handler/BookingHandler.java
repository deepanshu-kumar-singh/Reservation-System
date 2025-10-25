package com.example.travel.adapter.handler;

public interface BookingHandler {
    void setNext(BookingHandler handler);
    String handle(String request);
}
