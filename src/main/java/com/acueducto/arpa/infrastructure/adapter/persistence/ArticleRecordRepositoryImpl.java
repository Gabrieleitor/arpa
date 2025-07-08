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

    public ArticleRecordRepositoryImpl(JpaArticleRecordRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ArticleRecordDto save(ArticleRecordDto articleRecord) {
        ArticleRecordEntity entity = ArticleRecordMapper.toEntity(articleRecord);
        return ArticleRecordMapper.toDomain(jpaRepository.save(entity));
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