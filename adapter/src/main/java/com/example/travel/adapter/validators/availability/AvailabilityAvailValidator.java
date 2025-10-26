package com.example.travel.adapter.validators.availability;

import com.example.travel.adapter.validators.availability.fieldvalidators.DateAvailValidator;
import com.example.travel.adapter.validators.availability.fieldvalidators.EndPointAvailValidator;
import com.example.travel.adapter.validators.availability.fieldvalidators.StartPointAvailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AvailabilityAvailValidator implements AvailValidator<Map<String, Object>> {

    private final List<AvailValidator<Map<String, Object>>> fieldAvailValidators;

    public AvailabilityAvailValidator(DateAvailValidator dateValidator,
                                      StartPointAvailValidator startPointValidator,
                                      EndPointAvailValidator endPointValidator) {
        this.fieldAvailValidators = List.of(
                dateValidator,
                startPointValidator,
                endPointValidator
        );
    }

    @Override
    public List<String> validate(Map<String, Object> availabilityDetails) {
        List<String> errors = new ArrayList<>();
        if (availabilityDetails == null) {
            errors.add("Availability details cannot be null.");
            return errors;
        }

        for (AvailValidator<Map<String, Object>> availValidator : fieldAvailValidators) {
            errors.addAll(availValidator.validate(availabilityDetails));
        }

        return errors;
    }
}
