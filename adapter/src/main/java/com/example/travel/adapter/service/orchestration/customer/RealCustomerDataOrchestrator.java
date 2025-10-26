package com.example.travel.adapter.service.orchestration.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Profile("!mock")
public class RealCustomerDataOrchestrator implements CustomerDataOrchestrator {

    private final RestTemplate restTemplate;

    public RealCustomerDataOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String processName(String name) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/name", name, String.class);
    }

    @Override
    public String processPhoneNumber(String phoneNumber) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/phone", phoneNumber, String.class);
    }
}
