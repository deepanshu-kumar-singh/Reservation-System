package com.example.travel.adapter.validators.name;

import com.example.travel.adapter.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NameValidator implements Validator<String> {

    @Override
    public List<String> validate(String name) {
        List<String> errors = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            errors.add("Name cannot be null or empty.");
        }
        // Add more specific name validation rules here if needed

        return errors;
    }
}
