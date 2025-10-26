package com.example.travel.adapter.validators.sell;

import com.example.travel.adapter.validators.sell.fieldvalidators.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SellValidator implements Validator<Map<String, Object>> {

    private final List<Validator<Map<String, Object>>> fieldValidators;

    public SellValidator(AirlineCodeValidator airlineCodeValidator,
                         ServiceClassValidator serviceClassValidator,
                         DateValidator dateValidator,
                         StartPointValidator startPointValidator,
                         EndPointValidator endPointValidator,
                         SeatsValidator seatsValidator) {
        this.fieldValidators = List.of(
                airlineCodeValidator,
                serviceClassValidator,
                dateValidator,
                startPointValidator,
                endPointValidator,
                seatsValidator
        );
    }

    @Override
    public List<String> validate(Map<String, Object> flightDetails) {
        List<String> errors = new ArrayList<>();
        if (flightDetails == null) {
            errors.add("Flight details cannot be null");
            return errors;
        }

        for (Validator<Map<String, Object>> validator : fieldValidators) {
            errors.addAll(validator.validate(flightDetails));
        }

        return errors;
    }
}
