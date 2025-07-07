package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;

public class IdentificationTypeMapper {
    public static IdentificationTypeDto toDomain(IdentificationTypeEntity entity) {
        return new IdentificationTypeDto(
            entity.getId(),
            new Name(entity.getName())
        );
    }

    public static IdentificationTypeEntity toEntity(IdentificationTypeDto dto) {
        IdentificationTypeEntity entity = new IdentificationTypeEntity();
        entity.setId(dto.id());
        entity.setName(dto.name().value());
        return entity;
    }

    public static IdentificationTypeDto toDomain(IdentificationTypeRequest request) {
        return new IdentificationTypeDto(
            null,
            new Name(request.name())
        );
    }

    public static IdentificationTypeResponse toResponse(IdentificationTypeDto dto) {
        return new IdentificationTypeResponse(
            dto.id(),
            dto.name().value()
        );
    }
} 