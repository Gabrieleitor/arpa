package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.*;

import java.time.LocalDateTime;

public record ArticleRecordDto(
    Long id,
    Name name,
    Name lastName,
    Serial serial,
    ArticleStatus status,
    ArticleTypeDto articleType,
    IdentificationTypeDto identificationType,
    PersonTypeDto personType,
    String identificationNumber,
    MakeDto make,
    Comment comment,
    LocalDateTime entryDate,
    LocalDateTime exitDate
) {} 