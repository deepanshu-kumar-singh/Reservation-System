package com.example.travel.adapter.handler;

import com.example.travel.adapter.service.booking.customer.CustomerService;
import com.example.travel.adapter.validators.name.NameValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                Map<String, Object> nameDetails = new HashMap<>();
                nameDetails.put("RequestType", "name");
                nameDetails.put("name", matcher.group(1));
                List<String> errors = validator.validate(nameDetails);
                if (errors.isEmpty()) {
                    return customerService.processName(nameDetails);
                }
                return "ERR-002";
            }
            return handleNext(request);
        }
}
