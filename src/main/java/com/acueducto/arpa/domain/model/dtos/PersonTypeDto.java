package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.Name;

public record PersonTypeDto(Long id, Name name) {
    public PersonTypeDto(Long id) {
        this(id, null);
    }

}