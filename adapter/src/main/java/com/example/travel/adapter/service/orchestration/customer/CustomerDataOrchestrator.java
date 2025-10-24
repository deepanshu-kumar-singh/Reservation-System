package com.example.travel.adapter.service.orchestration.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerDataOrchestrator {

    private final RestTemplate restTemplate;

    public CustomerDataOrchestrator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String processNameQuery(String name) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/name", name, String.class);
    }

    public String processPhoneNumberQuery(String phoneNumber) {
        return restTemplate.postForObject("http://customer-data-service/api/v1/customer/phone", phoneNumber, String.class);
    }
}
