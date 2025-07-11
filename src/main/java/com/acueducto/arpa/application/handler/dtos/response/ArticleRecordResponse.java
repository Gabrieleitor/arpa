package com.acueducto.arpa.application.handler.dtos.response;

public record ArticleRecordResponse(
    Long id,
    String name,
    String lastName,
    String serial,
    String status,
    String articleTypeName,
    String identificationTypeName,
    String personTypeName,
    String comment,
    String createdAt,
    String makerName,
    String identificationNumber
) {} 