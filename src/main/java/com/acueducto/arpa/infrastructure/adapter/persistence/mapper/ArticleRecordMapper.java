package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.model.vo.*;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleRecordMapper {

    public static ArticleRecordDto toDomain(ArticleRecordEntity entity) {
        return new ArticleRecordDto(
                entity.getId(),
                new Name(entity.getName()),
                new Serial(entity.getSerial()),
                ArticleStatus.valueOf(entity.getStatus().name()),
                new ArticleTypeDto(
                        entity.getArticleTypeEntity().getId(),
                        new Name(entity.getArticleTypeEntity().getName())
                ),
                new IdentificationTypeDto(
                        entity.getIdentificationTypeEntity().getId(),
                        new Name(entity.getIdentificationTypeEntity().getName())
                ),
                new PersonTypeDto(
                        entity.getPersonTypeEntity().getId(),
                        new Name(entity.getPersonTypeEntity().getName())
                ),
                entity.getIdentificationNumber(),
                new Make(entity.getMake()),
                new Comment(entity.getComment()),
                entity.getEntryDate(),
                entity.getExitDate() != null ? entity.getExitDate() : null

        );
    }

    public static ArticleRecordEntity toEntity(ArticleRecordDto dto) {
        ArticleRecordEntity entity = new ArticleRecordEntity();
        if (dto.id() != null) {
            entity.setId(dto.id());
            entity.setExitDate(dto.exitDate());
        }
        entity.setArticleTypeEntity(
                new ArticleTypeEntity(
                        dto.articleType().id(),
                        dto.articleType().name().value()
                )
        );
        entity.setIdentificationTypeEntity(
                new IdentificationTypeEntity(
                        dto.identificationType().id(),
                        dto.identificationType().name().value()
                )
        );
        entity.setPersonTypeEntity(
                new PersonTypeEntity(
                        dto.personType().id(),
                        dto.personType().name().value()
                ))
        ;
        entity.setName(dto.name().value());
        entity.setSerial(dto.serial().value());
        entity.setStatus(ArticleRecordEntity.ArticleStatus.valueOf(dto.status().name()));
        entity.setMake(dto.make().value());
        entity.setComment(dto.comment().value());
        entity.setIdentificationNumber(dto.identificationNumber());
        entity.setEntryDate(dto.entryDate());
        return entity;
    }

    public static ArticleRecordDto toDomain(ArticleRecordRequest request) {
        return new ArticleRecordDto(
                null,
                new Name(request.name()),
                new Serial(request.serialNumber()),
                null,
                null, // articleType
                null, // identificationType
                null, // personType
                null, // comment
                null, // entryDate
                null, // make
                null,
                null// identificationNumber
        );
    }

    public static ArticleRecordResponse toResponse(ArticleRecordDto dto) {
        return new ArticleRecordResponse(
                dto.id(),
                dto.name().value(),
                dto.serial().value(),
                dto.status().name(),
                dto.articleType().name().value(),
                dto.identificationType().name().value(),
                dto.personType().name().value(),
                dto.comment().value(),
                dto.entryDate().toString(),
                dto.make().value(),
                dto.identificationNumber()
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