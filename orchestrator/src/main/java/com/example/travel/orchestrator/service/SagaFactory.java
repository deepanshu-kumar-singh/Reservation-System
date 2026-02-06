package com.example.travel.orchestrator.service;

@FunctionalInterface
public interface SagaFactory {
    Saga createSaga();
}
