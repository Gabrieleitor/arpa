package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonTypeService {
    private static final Logger log = LoggerFactory.getLogger(PersonTypeService.class);
    private PersonTypeRepository personTypeRepository;

    public PersonTypeService(PersonTypeRepository personTypeRepository) {
        this.personTypeRepository = personTypeRepository;
    }

    public List<PersonTypeDto> findAll() {
        log.info("Listing all person types");
        try {
            List<PersonTypeDto> list = personTypeRepository.list();
            log.info("Found {} person types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Error listing person types", e);
            throw e;
        }
    }

    public PersonTypeDto create(PersonTypeDto PersonTypeDto) {
        log.info("Creating person type: {}", PersonTypeDto);
        try {
            PersonTypeDto created = personTypeRepository.create(PersonTypeDto);
            log.info("Person type created successfully: {}", created);
            return created;
        } catch (Exception e) {
            log.error("Error creating person type", e);
            throw e;
        }
    }

    public Optional<PersonTypeDto> update(Long id, PersonTypeDto PersonTypeDto) {
        log.info("Updating person type: id={}", id);
        try {
            Optional<PersonTypeDto> updated = personTypeRepository.update(id, PersonTypeDto);
            if (updated.isPresent()) {
                log.info("Person type updated successfully: id={}", id);
            } else {
                log.warn("Person type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Error updating person type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Deleting person type: id={}", id);
        try {
            boolean deleted = personTypeRepository.delete(id);
            if (deleted) {
                log.info("Person type deleted successfully: id={}", id);
            } else {
                log.warn("Person type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Error deleting person type: id={}", id, e);
            throw e;
        }
    }
}
