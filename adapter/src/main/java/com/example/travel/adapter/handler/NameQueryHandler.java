package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.customer.CustomerQueryService;
import com.example.travel.adapter.validators.name.NameValidator;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameQueryHandler extends AbstractBookingFlowHandler {

    private static final Pattern NAME_QUERY_PATTERN = Pattern.compile(".*my name is (.*)", Pattern.CASE_INSENSITIVE);
    private final NameValidator validator;
    private final CustomerQueryService customerQueryService;

    public NameQueryHandler(CustomerQueryService customerQueryService, NameValidator validator) {
        this.customerQueryService = customerQueryService;
        this.validator = validator;
    }

    @Override
    public String handle(String request) {
        Matcher matcher = NAME_QUERY_PATTERN.matcher(request);
        if (matcher.matches()) {
            String name = matcher.group(1).trim();
            List<String> errors = validator.validate(name);
            if (errors.isEmpty()) {
                return customerQueryService.processNameQuery(name);
            }
            return "VALIDATION FAILED";
        }
        return handleNext(request);
    }
}
