package com.example.travel.adapter.router;

import com.example.travel.adapter.dto.SellRequestParser;
import com.example.travel.adapter.error.ErrorResponseFactory;
import com.example.travel.adapter.handler.*;
import com.example.travel.adapter.service.booking.availability.AvailabilityService;
import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.service.booking.flight.SellService;
import com.example.travel.adapter.validators.availability.AvailabilityAvailValidator;
import com.example.travel.adapter.validators.sell.SellValidator;
import com.example.travel.adapter.validators.name.NameValidator;
import com.example.travel.adapter.validators.phonenumber.PhoneNumberValidator;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

    private final SellService sellService;
    private final AvailabilityService availabilityService;
    private final CustomerService customerService;
    private final SellRequestParser parser;
    private final SellValidator sellValidator;
    private final AvailabilityAvailValidator availabilityValidator;
    private final NameValidator nameValidator;
    private final PhoneNumberValidator phoneNumberValidator;
    private final ErrorResponseFactory errorResponseFactory;

    public HandlerFactory(SellService sellService,
                          AvailabilityService availabilityService,
                          CustomerService customerService,
                          SellRequestParser parser,
                          SellValidator sellValidator,
                          AvailabilityAvailValidator availabilityValidator,
                          NameValidator nameValidator,
                          PhoneNumberValidator phoneNumberValidator,
                          ErrorResponseFactory errorResponseFactory) {
        this.sellService = sellService;
        this.availabilityService = availabilityService;
        this.customerService = customerService;
        this.parser = parser;
        this.sellValidator = sellValidator;
        this.availabilityValidator = availabilityValidator;
        this.nameValidator = nameValidator;
        this.phoneNumberValidator = phoneNumberValidator;
        this.errorResponseFactory = errorResponseFactory;
    }

    public BookingHandler linkableChain() {
        BookingHandler flightBookingHandler = new SellHandler(errorResponseFactory, sellService, parser, sellValidator);
        BookingHandler availabilityHandler = new AvailabilityHandler(errorResponseFactory, availabilityService, availabilityValidator);
        BookingHandler nameQueryHandler = new NameHandler(errorResponseFactory, customerService, nameValidator);
        BookingHandler phoneNumberQueryHandler = new PhoneNumberHandler(errorResponseFactory, customerService, phoneNumberValidator);

        flightBookingHandler.setNext(availabilityHandler);
        availabilityHandler.setNext(nameQueryHandler);
        nameQueryHandler.setNext(phoneNumberQueryHandler);

        return flightBookingHandler;
    }
}
