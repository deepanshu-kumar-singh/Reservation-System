package com.example.travel.adapter.validators.sell.fieldvalidators;

import com.example.travel.adapter.validators.sell.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AirlineCodeValidator implements Validator<Map<String, Object>> {

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        Object airlineCodeObj = flightDetails.get("airlineCode");

        if (!(airlineCodeObj instanceof String) || ((String) airlineCodeObj).trim().isEmpty()) {
            errors.add("Airline code is missing");
        } else {
            String airlineCode = ((String) airlineCodeObj).trim();
            if (!airlineCode.matches("[a-zA-Z]{2}")) {
                errors.add("Invalid Airline code");
            }
        }

        return errors;
    }
}
