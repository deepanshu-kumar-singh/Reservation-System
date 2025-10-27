package com.example.travel.adapter.service.booking.customer;

import com.example.travel.adapter.service.orchestration.customer.CustomerDataOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerService {

    private final CustomerDataOrchestrator orchestrator;

    public CustomerService(CustomerDataOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String processName(Map<String, Object> name) {
        return orchestrator.processName(name);
    }

    public String processPhoneNumber(Map<String, Object> phoneNumber) {
        return orchestrator.processPhoneNumber(phoneNumber);
    }
}
