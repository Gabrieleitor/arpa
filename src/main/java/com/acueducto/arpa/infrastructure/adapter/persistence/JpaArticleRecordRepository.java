package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaArticleRecordRepository extends JpaRepository<ArticleRecordEntity, Long> {

    List<ArticleRecordEntity> findByStatusOrderByEntryDateDesc(ArticleStatusEnum status);
} 