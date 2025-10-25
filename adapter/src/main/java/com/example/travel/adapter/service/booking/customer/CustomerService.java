package com.example.travel.adapter.service.booking.customer;

import com.example.travel.adapter.service.orchestration.customer.CustomerDataOrchestrator;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerDataOrchestrator orchestrator;

    public CustomerService(CustomerDataOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String processName(String name) {
        return orchestrator.processName(name);
    }

    public String processPhoneNumber(String phoneNumber) {
        return orchestrator.processPhoneNumber(phoneNumber);
    }
}
