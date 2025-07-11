package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.domain.model.dtos.*;
import com.acueducto.arpa.domain.model.vo.*;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.*;
import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleStatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleRecordMapper {

    public static ArticleRecordDto toDomain(ArticleRecordEntity entity) {
        return new ArticleRecordDto(
                entity.getId(),
                new Name(entity.getName()),
                new Name(entity.getLastName()),
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
                new MakeDto(entity.getMake().getId(), new Name(entity.getMake().getName())),
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
        entity.setStatus(ArticleStatusEnum.valueOf(dto.status().name()));
        entity.setMake(new MakeEntity(dto.make().id(), dto.make().name().value()));
        entity.setComment(dto.comment().value());
        entity.setIdentificationNumber(dto.identificationNumber());
        entity.setEntryDate(dto.entryDate());
        entity.setLastName(dto.lastName().value());
        return entity;
    }

    public static ArticleRecordDto toDomain(ArticleRecordRequest request) {
        return new ArticleRecordDto(
                null,
                new Name(request.name()),
                new Name(request.lastName()),
                new Serial(request.serialNumber()),
                ArticleStatus.ENTRY,
                new ArticleTypeDto(request.articleTypeId()),
                new IdentificationTypeDto(request.identificationTypeId()), // identificationType
                new PersonTypeDto(request.personTypeId()),
                request.identificationNumber(),
                new MakeDto(request.makeId()),
                new Comment(request.comment()),
                LocalDateTime.now(),
                null
        );
    }

    public static ArticleRecordDto toDomain(ArticleRecordDto request, ArticleTypeDto articleType,
                                            IdentificationTypeDto identificationType, PersonTypeDto personType, MakeDto makeDto) {
        return new ArticleRecordDto(
                null,
                request.name(),
                request.lastName(),
                request.serial(),
                request.status(),
                articleType,
                identificationType,
                personType,
                request.identificationNumber(),
                makeDto,
                request.comment(),
                request.entryDate(),
                null
        );
    }

    public static ArticleRecordDto toExitStatus(ArticleRecordDto article, LocalDateTime exitDate) {
        return new ArticleRecordDto(
                article.id(),
                article.name(),
                article.lastName(),
                article.serial(),
                ArticleStatus.EXIT,
                article.articleType(),
                article.identificationType(),
                article.personType(),
                article.identificationNumber(),
                article.make(),
                article.comment(),
                article.entryDate(),
                exitDate
        );
    }

    public static ArticleRecordResponse toResponse(ArticleRecordDto dto) {
        return new ArticleRecordResponse(
                dto.id(),
                dto.name().value(),
                dto.lastName().value(),
                dto.serial().value(),
                dto.status().name(),
                dto.articleType().name().value(),
                dto.identificationType().name().value(),
                dto.personType().name().value(),
                dto.comment().value(),
                dto.entryDate().toString(),
                dto.make().name().value(),
                dto.identificationNumber()
        );
    }

    public static List<ArticleRecordResponse> toResponseList(List<ArticleRecordDto> dtos) {
        if (dtos == null) {
            return null;
        }

        return dtos.stream()
                .map(ArticleRecordMapper::toResponse)
                .collect(Collectors.toList());
    }
}