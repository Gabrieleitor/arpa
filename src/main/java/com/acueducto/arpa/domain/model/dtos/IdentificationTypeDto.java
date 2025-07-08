package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.Name;

public record IdentificationTypeDto(Long id, Name name) {
    public IdentificationTypeDto(Long id) {
        this(id, null);
    }

}