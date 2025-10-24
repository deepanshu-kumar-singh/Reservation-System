package com.example.travel.adapter.router;

import com.example.travel.adapter.dto.FlightBookingRequestParser;
import com.example.travel.adapter.handler.*;
import com.example.travel.adapter.service.booking.availability.AvailabilityService;
import com.example.travel.adapter.service.booking.customer.CustomerQueryService;
import com.example.travel.adapter.service.booking.flight.FlightBookingService;
import com.example.travel.adapter.validators.availability.AvailabilityValidator;
import com.example.travel.adapter.validators.flightbooking.FlightBookingValidator;
import com.example.travel.adapter.validators.name.NameValidator;
import com.example.travel.adapter.validators.phonenumber.PhoneNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class HandlerChainFactory {

    private final FlightBookingService flightBookingService;
    private final AvailabilityService availabilityService;
    private final CustomerQueryService customerQueryService;
    private final FlightBookingRequestParser parser;
    private final FlightBookingValidator flightBookingValidator;
    private final AvailabilityValidator availabilityValidator;
    private final NameValidator nameValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    public HandlerChainFactory(FlightBookingService flightBookingService,
                               AvailabilityService availabilityService,
                               CustomerQueryService customerQueryService,
                               FlightBookingRequestParser parser,
                               FlightBookingValidator flightBookingValidator,
                               AvailabilityValidator availabilityValidator,
                               NameValidator nameValidator,
                               PhoneNumberValidator phoneNumberValidator) {
        this.flightBookingService = flightBookingService;
        this.availabilityService = availabilityService;
        this.customerQueryService = customerQueryService;
        this.parser = parser;
        this.flightBookingValidator = flightBookingValidator;
        this.availabilityValidator = availabilityValidator;
        this.nameValidator = nameValidator;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    public BookingFlowHandler createChain() {
        BookingFlowHandler flightBookingHandler = new FlightBookingHandler(flightBookingService, parser, flightBookingValidator);
        BookingFlowHandler availabilityHandler = new AvailabilityHandler(availabilityService, availabilityValidator);
        BookingFlowHandler nameQueryHandler = new NameQueryHandler(customerQueryService, nameValidator);
        BookingFlowHandler phoneNumberQueryHandler = new PhoneNumberQueryHandler(customerQueryService, phoneNumberValidator);

        flightBookingHandler.setNext(availabilityHandler);
        availabilityHandler.setNext(nameQueryHandler);
        nameQueryHandler.setNext(phoneNumberQueryHandler);

        return flightBookingHandler;
    }
}
