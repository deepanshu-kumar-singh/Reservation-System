package com.example.travel.adapter.handler;

import com.example.travel.adapter.error.ErrorResponseFactory;
import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.validators.phonenumber.PhoneNumberValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberHandler extends AbstractBookingHandler {

    private static final Pattern PHONE_NUMBER_QUERY_PATTERN = Pattern.compile("^(\\d{10})$");
    private final PhoneNumberValidator validator;
    private final CustomerService customerService;

    public PhoneNumberHandler(ErrorResponseFactory errorResponseFactory, CustomerService customerService, PhoneNumberValidator validator) {
        super(errorResponseFactory);
        this.customerService = customerService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        Matcher matcher = PHONE_NUMBER_QUERY_PATTERN.matcher(request);
        if (matcher.matches()) {
            String phoneNumber = matcher.group(1);
            List<String> errors = validator.validate(phoneNumber);
            if (errors.isEmpty()) {
                return customerService.processPhoneNumber(phoneNumber);
            }
            return errorResponseFactory.createErrorResponse("40000");
        }
        return handleNext(request);
    }
}
