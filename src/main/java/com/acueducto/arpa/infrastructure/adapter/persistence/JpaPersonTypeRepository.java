package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPersonTypeRepository extends JpaRepository<PersonTypeEntity, Long> {
} 