package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdentificationTypeRepository extends JpaRepository<IdentificationTypeEntity, Long> {
} 