package com.example.travel.adapter.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FlightBookingRequestParser {

    private static final Pattern FLIGHT_BOOKING_PATTERN = Pattern.compile("^O([A-Z]{2})([A-Z])(\\d{2}[A-Z]{3})([A-Z]{3})([A-Z]{3})NN(\\d+)$", Pattern.CASE_INSENSITIVE);

    public Map<String, Object> parse(String request) {
        Matcher matcher = FLIGHT_BOOKING_PATTERN.matcher(request);
        if (matcher.matches()) {
            Map<String, Object> flightDetails = new HashMap<>();
            flightDetails.put("airlineCode", matcher.group(1));
            flightDetails.put("serviceClass", matcher.group(2));
            flightDetails.put("date", matcher.group(3));
            flightDetails.put("startPoint", matcher.group(4));
            flightDetails.put("endPoint", matcher.group(5));
            flightDetails.put("seats", Integer.parseInt(matcher.group(6)));
            return flightDetails;
        }
        return null;
    }

    public boolean matches(String request) {
        return FLIGHT_BOOKING_PATTERN.matcher(request).matches();
    }
}
