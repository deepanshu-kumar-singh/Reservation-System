package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.customer.CustomerQueryService;
import com.example.travel.adapter.validators.phonenumber.PhoneNumberValidator;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberQueryHandler extends AbstractBookingFlowHandler {

    private static final Pattern PHONE_NUMBER_QUERY_PATTERN = Pattern.compile("^(\\d{10})$");
    private final PhoneNumberValidator validator;
    private final CustomerQueryService customerQueryService;

    public PhoneNumberQueryHandler(CustomerQueryService customerQueryService, PhoneNumberValidator validator) {
        this.customerQueryService = customerQueryService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        Matcher matcher = PHONE_NUMBER_QUERY_PATTERN.matcher(request);
        if (matcher.matches()) {
            String phoneNumber = matcher.group(1);
            List<String> errors = validator.validate(phoneNumber);
            if (errors.isEmpty()) {
                return customerQueryService.processPhoneNumberQuery(phoneNumber);
            }
            return "VALIDATION FAILED";
        }
        return handleNext(request);
    }
}
