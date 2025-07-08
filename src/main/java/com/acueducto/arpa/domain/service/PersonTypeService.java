package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
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

    public List<PersonTypeDto> findAll() {
        return personTypeRepository.list();
    }

    public PersonTypeDto create(PersonTypeDto PersonTypeDto) {
        return personTypeRepository.create(PersonTypeDto);
    }

    public Optional<PersonTypeDto> update(Long id, PersonTypeDto PersonTypeDto) {
        return personTypeRepository.update(id, PersonTypeDto);
    }

    public boolean delete(Long id) {
        return personTypeRepository.delete(id);
    }
}
