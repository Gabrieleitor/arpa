package com.acueducto.arpa.application.handler.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ArticleRecordRequest(
    @NotNull @Size(min = 1, max = 100) String name,
    @NotNull String serial,
    @NotNull String status
    // Agregar otros campos relevantes según el dominio
) {} 