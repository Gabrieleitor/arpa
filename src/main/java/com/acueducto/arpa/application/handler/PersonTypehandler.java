package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.PersonTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.PersonTypeResponse;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.service.PersonTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.PersonTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypehandler {
    private PersonTypeService personTypeService;
    private static final Logger log = LoggerFactory.getLogger(PersonTypehandler.class);

    public PersonTypehandler(PersonTypeService personTypeService) {
        this.personTypeService = personTypeService;
    }

    public List<PersonTypeResponse> list() {
        log.info("Handler: Listing all person types");
        try {
            List<PersonTypeResponse> list = personTypeService.findAll().stream()
                    .map(PersonTypeMapper::toResponse)
                    .toList();
            log.info("Handler: Found {} person types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Handler: Error listing person types", e);
            throw e;
        }
    }

    public PersonTypeResponse create(PersonTypeRequest personType) {
        log.info("Handler: Creating person type: {}", personType);
        try {
            PersonTypeDto personTypeDto = PersonTypeMapper.toDomain(personType);
            PersonTypeResponse response = PersonTypeMapper.toResponse(personTypeService.create(personTypeDto));
            log.info("Handler: Person type created successfully: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Handler: Error creating person type", e);
            throw e;
        }
    }

    public Optional<PersonTypeResponse> update(Long id, PersonTypeRequest personType) {
        log.info("Handler: Updating person type: id={}", id);
        try {
            PersonTypeDto personTypeDto = PersonTypeMapper.toDomain(personType);
            Optional<PersonTypeResponse> updated = personTypeService.update(id, personTypeDto)
                    .map(PersonTypeMapper::toResponse);
            if (updated.isPresent()) {
                log.info("Handler: Person type updated successfully: id={}", id);
            } else {
                log.warn("Handler: Person type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Handler: Error updating person type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Handler: Deleting person type: id={}", id);
        try {
            boolean deleted = personTypeService.delete(id);
            if (deleted) {
                log.info("Handler: Person type deleted successfully: id={}", id);
            } else {
                log.warn("Handler: Person type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Handler: Error deleting person type: id={}", id, e);
            throw e;
        }
    }
}
