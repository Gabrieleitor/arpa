package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;

import java.util.List;
import java.util.Optional;

public interface PersonTypeRepository {

    Optional<PersonTypeEntity> update(Long id, PersonTypeEntity identificationType);

    List<PersonTypeEntity> list();

    PersonTypeEntity create(PersonTypeEntity identificationType);

    boolean delete(Long id);

    Optional<PersonTypeEntity> findById(Long id);
}
