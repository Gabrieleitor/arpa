package com.acueducto.arpa.application.handler.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IdentificationTypeRequest(
    @NotNull @Size(min = 1, max = 100) String name
) {} 