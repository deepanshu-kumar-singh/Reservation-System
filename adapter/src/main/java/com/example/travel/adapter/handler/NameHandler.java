package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.validators.name.NameValidator;

import java.util.List;
import java.util.regex.Matcher;

import static com.example.travel.adapter.constant.RegexConstant.NAME_QUERY_PATTERN;

public class NameHandler extends AbstractBookingHandler {


    private final NameValidator validator;
    private final CustomerService customerService;

    public NameHandler(CustomerService customerService, NameValidator validator) {
        this.customerService = customerService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
            Matcher matcher = NAME_QUERY_PATTERN.matcher(request);
            if (matcher.matches()) {
                String name = matcher.group(1).trim();
                List<String> errors = validator.validate(name);
                if (errors.isEmpty()) {
                    return customerService.processName(name);
                }
                return "ERR-002";
            }
            return handleNext(request);
        }
}
