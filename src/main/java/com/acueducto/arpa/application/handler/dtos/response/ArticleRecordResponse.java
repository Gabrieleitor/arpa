package com.acueducto.arpa.application.handler.dtos.response;

public record ArticleRecordResponse(
    Long id,
    String name,
    String serial,
    String status
    // Agregar otros campos relevantes según el dominio
) {} 