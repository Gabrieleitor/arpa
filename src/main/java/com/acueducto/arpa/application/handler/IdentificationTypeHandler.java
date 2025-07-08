package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.domain.service.IdentificationTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.IdentificationTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentificationTypeHandler {

    private final IdentificationTypeService identificationTypeService;

    public IdentificationTypeHandler(IdentificationTypeService identificationTypeService) {
        this.identificationTypeService = identificationTypeService;
    }

    public List<IdentificationTypeResponse> list() {
        return identificationTypeService.list()
                .stream()
                .map(IdentificationTypeMapper::toResponse)
                .toList();
    }

    public IdentificationTypeResponse create(IdentificationTypeRequest identificationTypeDto) {
        IdentificationTypeDto identificationType = IdentificationTypeMapper.toDomain(identificationTypeDto);
        return IdentificationTypeMapper.toResponse(identificationTypeService.create(identificationType));
    }

    public Optional<IdentificationTypeResponse> update(Long id, IdentificationTypeRequest identificationType) {
        IdentificationTypeDto identificationTypeDto = IdentificationTypeMapper.toDomain(identificationType);
        return identificationTypeService.update(id, identificationTypeDto)
                .map(IdentificationTypeMapper::toResponse);
    }

    public boolean delete(Long id) {
        return identificationTypeService.delete(id);
    }
}
