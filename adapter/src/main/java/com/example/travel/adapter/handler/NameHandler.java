package com.example.travel.adapter.handler;

import com.example.travel.adapter.error.ErrorResponseFactory;
import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.validators.name.NameValidator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameHandler extends AbstractBookingHandler {

    private static final Pattern NAME_QUERY_PATTERN = Pattern.compile(".*my name is (.*)", Pattern.CASE_INSENSITIVE);
    private final NameValidator validator;
    private final CustomerService customerService;

    public NameHandler(ErrorResponseFactory errorResponseFactory, CustomerService customerService, NameValidator validator) {
        super(errorResponseFactory);
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
            return errorResponseFactory.createErrorResponse("ERR-002");
        }
        return handleNext(request);
    }
}
