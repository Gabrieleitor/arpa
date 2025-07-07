package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleRecordRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleRecordRepositoryImpl implements ArticleRecordRepository {

    private final JpaArticleRecordRepository jpaRepository;

    public ArticleRecordRepositoryImpl(JpaArticleRecordRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ArticleRecordEntity save(ArticleRecordEntity articleRecordEntity) {
        return jpaRepository.save(articleRecordEntity);
    }

    @Override
    public Optional<ArticleRecordEntity> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<ArticleRecordEntity> findAll() {
        return jpaRepository.findAll();
    }

} 