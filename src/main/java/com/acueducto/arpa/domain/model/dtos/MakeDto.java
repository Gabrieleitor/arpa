package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.Name;

public record MakeDto(Long id, Name name) {

    public MakeDto(Long id) {
        this(id, null);
    }
}
