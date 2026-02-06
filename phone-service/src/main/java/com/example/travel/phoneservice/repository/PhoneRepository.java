package com.example.travel.phoneservice.repository;

import com.example.travel.phoneservice.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    void deleteBySagaId(String sagaId);
}
