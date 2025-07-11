package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.application.handler.dtos.request.MakeRequest;
import com.acueducto.arpa.application.handler.dtos.response.MakeResponse;
import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.MakeEntity;

public class MakeMapper {
    public static MakeDto toDomain(MakeEntity entity) {
        return new MakeDto(
                entity.getId(),
                new Name(entity.getName())
        );
    }

    public static MakeEntity toEntity(MakeDto dto) {
        MakeEntity entity = new MakeEntity();
        entity.setId(dto.id());
        entity.setName(dto.name().value());
        return entity;
    }

    public static MakeDto toDomain(MakeRequest request) {
        return new MakeDto(null, new Name(request.name()));
    }

    public static MakeResponse toResponse(MakeDto makeDto) {
        return new MakeResponse(
                makeDto.id(),
                makeDto.name().value()
        );
    }
}
