package com.example.travel.adapter.service.orchestration.customer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock")
public class MockCustomerDataOrchestrator implements CustomerDataOrchestrator {

    @Override
    public String processName(String name) {
        System.out.println("--- MOCK CUSTOMER DATA ORCHESTRATOR --- Input Name: " + name);
        return "Mock Response: Customer profile found for name: " + name;
    }

    @Override
    public String processPhoneNumber(String phoneNumber) {
        System.out.println("--- MOCK CUSTOMER DATA ORCHESTRATOR --- Input Phone: " + phoneNumber);
        return "Mock Response: Customer profile found for phone number: " + phoneNumber;
    }
}
