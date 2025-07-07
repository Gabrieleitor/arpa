package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;

public class ArticleTypeMapper {
    public static ArticleTypeDto toDomain(ArticleTypeEntity entity) {
        return new ArticleTypeDto(
            entity.getId(),
            new Name(entity.getName())
        );
    }

    public static ArticleTypeEntity toEntity(ArticleTypeDto dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.id());
        entity.setName(dto.name().value());
        return entity;
    }

    public static ArticleTypeDto toDomain(ArticleTypeRequest request) {
        return new ArticleTypeDto(
            null,
            new Name(request.name())
        );
    }

    public static ArticleTypeResponse toResponse(ArticleTypeDto dto) {
        return new ArticleTypeResponse(
            dto.id(),
            dto.name().value()
        );
    }
} 