package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;

import java.util.List;
import java.util.Optional;

public interface PersonTypeRepository {

    Optional<PersonTypeDto> update(Long id, PersonTypeDto identificationTypeDto);

    List<PersonTypeDto> list();

    PersonTypeDto create(PersonTypeDto identificationDto);

    boolean delete(Long id);

    Optional<PersonTypeDto> findById(Long id);
}
