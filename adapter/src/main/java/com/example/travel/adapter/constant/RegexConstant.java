package com.example.travel.adapter.constant;

import java.util.regex.Pattern;

public class RegexConstant {
    public static final Pattern PHONE_NUMBER_QUERY_PATTERN = Pattern.compile("^(\\d{10})$");
    public static final Pattern NAME_QUERY_PATTERN = Pattern.compile(".*my name is (.*)", Pattern.CASE_INSENSITIVE);
    public static final Pattern AVAILABILITY_PATTERN = Pattern.compile("^START\\s+(\\d{1,2}[A-Z]{3})([A-Z]{3})([A-Z]{3})$", Pattern.CASE_INSENSITIVE);
    public static final Pattern FLIGHT_BOOKING_PATTERN = Pattern.compile("^O([A-Z]{2})([A-Z])(\\d{2}[A-Z]{3})([A-Z]{3})([A-Z]{3})NN(\\d+)$", Pattern.CASE_INSENSITIVE);
    public static final String DATE_REGEX = "\\d{2}[a-zA-Z]{3}";
}
