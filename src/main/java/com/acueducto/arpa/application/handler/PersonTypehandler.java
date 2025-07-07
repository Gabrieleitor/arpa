package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.domain.service.PersonTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypehandler {
    private PersonTypeService personTypeService;

    public PersonTypehandler(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    public List<PersonTypeEntity> list() {
        return personTypeService.findAll();
    }

    public PersonTypeEntity create(PersonTypeEntity personTypeEntity) {
        return personTypeService.create(personTypeEntity);
    }

    public Optional<PersonTypeEntity> update(Long id, PersonTypeEntity personTypeEntity) {
        return personTypeService.update(id, personTypeEntity);
    }

    public boolean delete(Long id) {
        return personTypeService.delete(id);
    }
}
