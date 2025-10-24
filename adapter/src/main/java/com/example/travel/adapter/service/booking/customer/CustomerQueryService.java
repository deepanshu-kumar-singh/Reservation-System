package com.example.travel.adapter.service.booking.customer;

import com.example.travel.adapter.service.orchestration.customer.CustomerDataOrchestrator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerQueryService {

    private final CustomerDataOrchestrator orchestrator;

    public CustomerQueryService(CustomerDataOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public String processNameQuery(String name) {
        String result = orchestrator.processNameQuery(name);
        return result;
    }

    public String processPhoneNumberQuery(String phoneNumber) {
        String result = orchestrator.processPhoneNumberQuery(phoneNumber);
        return result;
    }
}
