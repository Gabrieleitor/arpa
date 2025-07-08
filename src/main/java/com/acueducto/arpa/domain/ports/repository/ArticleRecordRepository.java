package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import java.util.List;
import java.util.Optional;

public interface ArticleRecordRepository {
    ArticleRecordDto save(ArticleRecordDto articleRecordEntity);
    Optional<ArticleRecordDto> findById(Long id);
    List<ArticleRecordDto> findAll();
} 