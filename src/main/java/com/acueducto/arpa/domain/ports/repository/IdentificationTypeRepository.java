package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;

import java.util.List;
import java.util.Optional;

public interface IdentificationTypeRepository {
    Optional<IdentificationTypeEntity> update(Long id, IdentificationTypeEntity identificationTypeEntity);

    List<IdentificationTypeEntity> list();

    IdentificationTypeEntity create(IdentificationTypeEntity identificationTypeEntity);

    boolean delete(Long id);

    Optional<IdentificationTypeEntity> findById(Long id);
}
