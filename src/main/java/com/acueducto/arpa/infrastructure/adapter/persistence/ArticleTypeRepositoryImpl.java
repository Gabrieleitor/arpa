package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArticleTypeRepositoryImpl implements ArticleTypeRepository {
    private final JpaArticleTypeRepository jpaRepository;

    public ArticleTypeRepositoryImpl(JpaArticleTypeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<ArticleTypeDto> update(Long id, ArticleTypeDto type) {
        return jpaRepository.findById(id).map(existingType -> {
            ArticleTypeEntity entity = ArticleTypeMapper.toEntity(type);
            entity.setId(id);
            return ArticleTypeMapper.toDomain(jpaRepository.save(entity));
        });
    }

    @Override
    public List<ArticleTypeDto> list() {
        return jpaRepository.findAll().stream()
                .map(ArticleTypeMapper::toDomain)
                .toList();
    }

    @Override
    public ArticleTypeDto create(ArticleTypeDto type) {
        return ArticleTypeMapper.toDomain(jpaRepository.save(ArticleTypeMapper.toEntity(type)));
    }

    @Override
    public boolean delete(Long id) {
        return jpaRepository.findById(id)
                .map(existingType -> {
                    jpaRepository.delete(existingType);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<ArticleTypeDto> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ArticleTypeMapper::toDomain);
    }
}
