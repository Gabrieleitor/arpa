package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.*;

import java.time.LocalDateTime;

public record ArticleRecordDto(
    Long id,
    Name name,
    Serial serial,
    ArticleStatus status,
    ArticleTypeDto articleType,
    IdentificationTypeDto identificationType,
    PersonTypeDto personType,
    String identificationNumber,
    Make make,
    Comment comment,
    LocalDateTime entryDate
) {} 