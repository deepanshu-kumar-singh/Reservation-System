package com.example.travel.nameservice.repository;

import com.example.travel.nameservice.entity.NameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NameRepository extends JpaRepository<NameEntity, Long> {
    void deleteBySagaId(String sagaId);
}
