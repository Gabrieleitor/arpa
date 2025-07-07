package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.model.vo.ArticleStatus;
import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleRecordMapper {

    public static ArticleRecordDto toDomain(ArticleRecordEntity entity) {
        return new ArticleRecordDto(
            entity.getId(),
            new Name(entity.getName()),
            new Serial(entity.getSerial()),
            ArticleStatus.valueOf(entity.getStatus())
        );
    }

    public static ArticleRecordEntity toEntity(ArticleRecordDto dto) {
        ArticleRecordEntity entity = new ArticleRecordEntity();
        entity.setId(dto.id());
        entity.setName(dto.name().value());
        entity.setSerial(dto.serial().value());
        entity.setStatus(dto.status().name());
        return entity;
    }

    public static ArticleRecordDto toDomain(ArticleRecordRequest request) {
        return new ArticleRecordDto(
            null,
            new Name(request.name()),
            new Serial(request.serial()),
            ArticleStatus.valueOf(request.status())
        );
    }

    public static ArticleRecordResponse toResponse(ArticleRecordDto dto) {
        return new ArticleRecordResponse(
            dto.id(),
            dto.name().value(),
            dto.serial().value(),
            dto.status().name()
        );
    }

    public static List<ArticleRecordDto> toDtoList(List<ArticleRecordEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(ArticleRecordMapper::toDomain)
                .collect(Collectors.toList());
    }
} 