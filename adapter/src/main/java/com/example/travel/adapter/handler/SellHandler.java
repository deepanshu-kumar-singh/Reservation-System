package com.example.travel.adapter.handler;

import com.example.travel.adapter.dto.SellRequestParser;
import com.example.travel.adapter.error.ErrorResponseFactory;
import com.example.travel.adapter.service.booking.flight.SellService;
import com.example.travel.adapter.validators.flightbooking.FlightBookingValidator;

import java.util.List;
import java.util.Map;

public class SellHandler extends AbstractBookingHandler {

    private final SellRequestParser parser;
    private final FlightBookingValidator validator;
    private final SellService sellService;

    public SellHandler(ErrorResponseFactory errorResponseFactory, SellService sellService, SellRequestParser parser, FlightBookingValidator validator) {
        super(errorResponseFactory);
        this.sellService = sellService;
        this.parser = parser;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        if (parser.matches(request)) {
            Map<String, Object> flightDetails = parser.parse(request);
            List<String> errors = validator.validate(flightDetails);
            if (errors.isEmpty()) {
                return sellService.bookFlight(flightDetails);
            }
            return errorResponseFactory.createErrorResponse("40000");
        }
        return handleNext(request);
    }
}
