package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.Name;

public record ArticleTypeDto(Long id, Name name) {
    public ArticleTypeDto(Long id) {
        this(id, null);
    }
} 