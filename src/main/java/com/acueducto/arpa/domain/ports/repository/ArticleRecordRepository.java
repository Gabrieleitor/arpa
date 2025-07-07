package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import java.util.List;
import java.util.Optional;

public interface ArticleRecordRepository {
    ArticleRecordEntity save(ArticleRecordEntity articleRecordEntity);
    Optional<ArticleRecordEntity> findById(Long id);
    List<ArticleRecordEntity> findAll();
} 