package com.example.travel.adapter.validators.name;

import com.example.travel.adapter.validators.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NameValidator implements Validator<String> {

    @Override
    public List<String> validate(Map<String, Object> name) {
        List<String> errors = new ArrayList<>();

        if (name == null ) {
            errors.add("Name cannot be null");
        }
        return errors;
    }
}
