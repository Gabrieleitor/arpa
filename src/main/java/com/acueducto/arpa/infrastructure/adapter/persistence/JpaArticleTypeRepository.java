package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArticleTypeRepository extends JpaRepository<ArticleTypeEntity, Long> {
} 