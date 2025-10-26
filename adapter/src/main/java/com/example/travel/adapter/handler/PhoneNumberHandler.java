package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.validators.phonenumber.PhoneNumberValidator;

import java.util.List;
import java.util.regex.Matcher;

import static com.example.travel.adapter.constant.RegexConstant.PHONE_NUMBER_QUERY_PATTERN;

public class PhoneNumberHandler extends AbstractBookingHandler {


    private final PhoneNumberValidator validator;
    private final CustomerService customerService;

    public PhoneNumberHandler(CustomerService customerService, PhoneNumberValidator validator) {
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
                return "ERR-002";
            }
            return handleNext(request);
        }
}
