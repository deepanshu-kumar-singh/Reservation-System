package com.example.travel.adapter.dto;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.travel.adapter.constant.RegexConstant.FLIGHT_BOOKING_PATTERN;

@Component
public class SellRequestParser {

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
            flightDetails.put("requestType", "sell");
            return flightDetails;
        }
        return null;
    }

    public boolean matches(String request) {
        return FLIGHT_BOOKING_PATTERN.matcher(request).matches();
    }
}
