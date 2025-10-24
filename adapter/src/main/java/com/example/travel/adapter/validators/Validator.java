package com.example.travel.adapter.validators;

import java.util.List;

public interface Validator<T> {
    List<String> validate(T input);
}
