package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
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
    public Optional<ArticleTypeEntity> update(Long id, ArticleTypeEntity type) {
        return jpaRepository.findById(id)
                .map(existingType -> {
                    existingType.setName(type.getName());
                    return jpaRepository.save(existingType);
                });
    }

    @Override
    public List<ArticleTypeEntity> list() {
        return jpaRepository.findAll();
    }

    @Override
    public ArticleTypeEntity create(ArticleTypeEntity type) {
        return jpaRepository.save(type);
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
    public Optional<ArticleTypeEntity> findById(Long id) {
        return jpaRepository.findById(id);
    }
}
