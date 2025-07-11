package com.acueducto.arpa.application.handler.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ArticleRecordRequest(
        @NotNull @Size(min = 1, max = 100) String name,
        @NotNull @Size(min = 1, max = 100) String lastName,
        @NotNull Long articleTypeId,
        @NotNull Long identificationTypeId,
        @NotNull Long personTypeId,
        @Size(max = 500) String comment,
        @NotNull Long makeId,
        @NotNull String identificationNumber,
        @NotNull String serialNumber

) {
}