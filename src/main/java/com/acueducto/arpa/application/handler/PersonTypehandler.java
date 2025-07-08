package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.PersonTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.PersonTypeResponse;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.service.PersonTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.PersonTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypehandler {
    private PersonTypeService personTypeService;

    public PersonTypehandler(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    public List<PersonTypeResponse> list() {
        return personTypeService.findAll().stream()
                .map(PersonTypeMapper::toResponse)
                .toList();
    }

    public PersonTypeResponse create(PersonTypeRequest personType) {
        PersonTypeDto personTypeDto = PersonTypeMapper.toDomain(personType);
        return PersonTypeMapper.toResponse(personTypeService.create(personTypeDto));
    }

    public Optional<PersonTypeResponse> update(Long id, PersonTypeRequest personType) {
        PersonTypeDto personTypeDto = PersonTypeMapper.toDomain(personType);
        return personTypeService.update(id, personTypeDto)
                .map(PersonTypeMapper::toResponse);
    }

    public boolean delete(Long id) {
        return personTypeService.delete(id);
    }
}
