package com.example.travel.adapter.service.orchestration.customer;

import java.util.Map;

public interface CustomerDataOrchestrator {
    String processName(Map<String, Object> name);
    String processPhoneNumber(Map<String, Object> phoneNumber);
}
