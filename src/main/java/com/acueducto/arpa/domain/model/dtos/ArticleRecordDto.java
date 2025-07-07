package com.acueducto.arpa.domain.model.dtos;

import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.model.vo.ArticleStatus;

public record ArticleRecordDto(
    Long id,
    Name name,
    Serial serial,
    ArticleStatus status
    // otros campos relevantes
) {} 