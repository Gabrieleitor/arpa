package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.application.handler.dtos.request.PersonTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.PersonTypeResponse;

public class PersonTypeMapper {
    public static PersonTypeDto toDomain(PersonTypeEntity entity) {
        return new PersonTypeDto(
                entity.getId(),
                new Name(entity.getName())
        );
    }

    public static PersonTypeEntity toEntity(PersonTypeDto dto) {
        PersonTypeEntity entity = new PersonTypeEntity();
        entity.setId(dto.id());
        entity.setName(dto.name().value());
        return entity;
    }

    public static PersonTypeDto toDomain(PersonTypeRequest request) {
        return new PersonTypeDto(
                null,
                new Name(request.name())
        );
    }

    public static PersonTypeResponse toResponse(PersonTypeDto dto) {
        return new PersonTypeResponse(
                dto.id(),
                dto.name().value()
        );
    }
} 