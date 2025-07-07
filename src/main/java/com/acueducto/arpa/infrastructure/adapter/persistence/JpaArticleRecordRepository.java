package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArticleRecordRepository extends JpaRepository<ArticleRecordEntity, Long> {
    // Métodos personalizados si los necesitas
} 