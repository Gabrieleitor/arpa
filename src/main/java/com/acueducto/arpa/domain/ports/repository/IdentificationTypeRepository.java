package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;

import java.util.List;
import java.util.Optional;

public interface IdentificationTypeRepository {
    Optional<IdentificationTypeDto> update(Long id, IdentificationTypeDto identificationType);

    List<IdentificationTypeDto> list();

    IdentificationTypeDto create(IdentificationTypeDto identificationType);

    boolean delete(Long id);

    Optional<IdentificationTypeDto> findById(Long id);
}
