package com.example.travel.adapter.validators;

import java.util.List;
import java.util.Map;

public interface Validator<T> {
    List<String> validate(Map<String, Object> phoneNumber);
}
