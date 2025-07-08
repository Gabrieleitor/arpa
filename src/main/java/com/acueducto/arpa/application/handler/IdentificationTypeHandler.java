package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.service.IdentificationTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.IdentificationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentificationTypeHandler {

    private static final Logger log = LoggerFactory.getLogger(IdentificationTypeHandler.class);

    private final IdentificationTypeService identificationTypeService;

    public IdentificationTypeHandler(IdentificationTypeService identificationTypeService) {
        this.identificationTypeService = identificationTypeService;
    }

    public List<IdentificationTypeResponse> list() {
        log.info("Handler: Listing all identification types");
        try {
            List<IdentificationTypeResponse> list = identificationTypeService.list()
                    .stream()
                    .map(IdentificationTypeMapper::toResponse)
                    .toList();
            log.info("Handler: Found {} identification types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Handler: Error listing identification types", e);
            throw e;
        }
    }

    public IdentificationTypeResponse create(IdentificationTypeRequest identificationTypeDto) {
        log.info("Handler: Creating identification type: {}", identificationTypeDto);
        try {
            IdentificationTypeDto identificationType = IdentificationTypeMapper.toDomain(identificationTypeDto);
            IdentificationTypeResponse response = IdentificationTypeMapper.toResponse(identificationTypeService.create(identificationType));
            log.info("Handler: Identification type created successfully: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Handler: Error creating identification type", e);
            throw e;
        }
    }

    public Optional<IdentificationTypeResponse> update(Long id, IdentificationTypeRequest identificationType) {
        log.info("Handler: Updating identification type: id={}", id);
        try {
            IdentificationTypeDto identificationTypeDto = IdentificationTypeMapper.toDomain(identificationType);
            Optional<IdentificationTypeResponse> updated = identificationTypeService.update(id, identificationTypeDto)
                    .map(IdentificationTypeMapper::toResponse);
            if (updated.isPresent()) {
                log.info("Handler: Identification type updated successfully: id={}", id);
            } else {
                log.warn("Handler: Identification type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Handler: Error updating identification type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Handler: Deleting identification type: id={}", id);
        try {
            boolean deleted = identificationTypeService.delete(id);
            if (deleted) {
                log.info("Handler: Identification type deleted successfully: id={}", id);
            } else {
                log.warn("Handler: Identification type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Handler: Error deleting identification type: id={}", id, e);
            throw e;
        }
    }
}
