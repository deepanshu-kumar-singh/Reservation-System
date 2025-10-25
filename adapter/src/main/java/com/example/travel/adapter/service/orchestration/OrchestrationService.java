package com.example.travel.adapter.service.orchestration;

import com.example.travel.adapter.dto.SellResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class OrchestrationService {

    private final RestTemplate restTemplate;

    public OrchestrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SellResponse bookFlight(Map<String, Object> flightDetails) {
        return restTemplate.postForObject("http://flight-booking-service/api/v1/flights", flightDetails, SellResponse.class);
    }

    public String checkAvailability(Map<String, Object> availabilityDetails) {
        return restTemplate.postForObject("http://availability-service/api/v1/availability", availabilityDetails, String.class);
    }

    public String processNameQuery(String name) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/name", name, String.class);
    }

    public String processPhoneNumberQuery(String phoneNumber) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/phone", phoneNumber, String.class);
    }
}
