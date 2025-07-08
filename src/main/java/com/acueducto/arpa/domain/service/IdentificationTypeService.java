package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentificationTypeService {
    private final IdentificationTypeRepository identificationTypeRepository;
    private static final Logger log = LoggerFactory.getLogger(IdentificationTypeService.class);

    public IdentificationTypeService(IdentificationTypeRepository identificationTypeRepository) {
        this.identificationTypeRepository = identificationTypeRepository;
    }

    public List<IdentificationTypeDto> list() {
        log.info("Listing all identification types");
        try {
            List<IdentificationTypeDto> list = identificationTypeRepository.list();
            log.info("Found {} identification types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Error listing identification types", e);
            throw e;
        }
    }

    public IdentificationTypeDto create(IdentificationTypeDto IdentificationTypeDto) {
        log.info("Creating identification type: {}", IdentificationTypeDto);
        try {
            IdentificationTypeDto created = identificationTypeRepository.create(IdentificationTypeDto);
            log.info("Identification type created successfully: {}", created);
            return created;
        } catch (Exception e) {
            log.error("Error creating identification type", e);
            throw e;
        }
    }

    public Optional<IdentificationTypeDto> update(Long id, IdentificationTypeDto IdentificationTypeDto) {
        log.info("Updating identification type: id={}", id);
        try {
            Optional<IdentificationTypeDto> updated = identificationTypeRepository.update(id, IdentificationTypeDto);
            if (updated.isPresent()) {
                log.info("Identification type updated successfully: id={}", id);
            } else {
                log.warn("Identification type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Error updating identification type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Deleting identification type: id={}", id);
        try {
            boolean deleted = identificationTypeRepository.delete(id);
            if (deleted) {
                log.info("Identification type deleted successfully: id={}", id);
            } else {
                log.warn("Identification type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Error deleting identification type: id={}", id, e);
            throw e;
        }
    }
}
