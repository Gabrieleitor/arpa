package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypeService {
    private PersonTypeRepository personTypeRepository;

    public PersonTypeService(PersonTypeRepository personTypeRepository) {
        this.personTypeRepository = personTypeRepository;
    }

    public List<PersonTypeEntity> findAll() {
        return personTypeRepository.list();
    }

    public PersonTypeEntity create(PersonTypeEntity personTypeEntity) {
        return personTypeRepository.create(personTypeEntity);
    }

    public Optional<PersonTypeEntity> update(Long id, PersonTypeEntity personTypeEntity) {
        return personTypeRepository.update(id, personTypeEntity);
    }

    public boolean delete(Long id) {
        return personTypeRepository.delete(id);
    }
}
