package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleRecordRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleRecordRepositoryImpl implements ArticleRecordRepository {

    private final JpaArticleRecordRepository jpaRepository;
    private final JpaArticleTypeRepository jpaArticleTypeRepository;
    private final JpaIdentificationTypeRepository jpaIdentificationTypeRepository;
    private final JpaPersonTypeRepository jpaPersonTypeRepository;

    public ArticleRecordRepositoryImpl(JpaArticleRecordRepository jpaRepository,
                                     JpaArticleTypeRepository jpaArticleTypeRepository,
                                     JpaIdentificationTypeRepository jpaIdentificationTypeRepository,
                                     JpaPersonTypeRepository jpaPersonTypeRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaArticleTypeRepository = jpaArticleTypeRepository;
        this.jpaIdentificationTypeRepository = jpaIdentificationTypeRepository;
        this.jpaPersonTypeRepository = jpaPersonTypeRepository;
    }

    @Override
    public ArticleRecordDto save(ArticleRecordDto articleRecord) {
        ArticleRecordEntity entity = ArticleRecordMapper.toEntity(articleRecord);
        loadRelatedEntities(entity, articleRecord);
        entity = jpaRepository.save(entity);
        return ArticleRecordMapper.toDomain(entity);
    }

    private void loadRelatedEntities(ArticleRecordEntity entity, ArticleRecordDto articleRecord) {
        entity.setArticleTypeEntity(
                jpaArticleTypeRepository.findById(articleRecord.articleType().id()).orElse(null)
        );
        entity.setIdentificationTypeEntity(
                jpaIdentificationTypeRepository.findById(articleRecord.identificationType().id()).orElse(null)
        );
        entity.setPersonTypeEntity(
                jpaPersonTypeRepository.findById(articleRecord.personType().id()).orElse(null)
        );
    }

    @Override
    public Optional<ArticleRecordDto> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ArticleRecordMapper::toDomain);
    }

    @Override
    public List<ArticleRecordDto> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(ArticleRecordMapper::toDomain)
                .toList();
    }

} 