package com.example.travel.adapter.handler;
import java.util.Map;

public interface BookingFlowHandler {
    void setNext(BookingFlowHandler handler);
    String handle(String request);
}
