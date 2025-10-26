package com.example.travel.adapter.validators.availability;

import com.example.travel.adapter.validators.availability.fieldvalidators.DateAvailValidator;
import com.example.travel.adapter.validators.availability.fieldvalidators.EndPointAvailValidator;
import com.example.travel.adapter.validators.availability.fieldvalidators.StartPointAvailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.travel.adapter.constant.ErrorCodes.ERR_002;

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
            errors.add(ERR_002);
            return errors;
        }

        for (AvailValidator<Map<String, Object>> availValidator : fieldAvailValidators) {
            errors.addAll(availValidator.validate(availabilityDetails));
        }

        return errors;
    }
}
