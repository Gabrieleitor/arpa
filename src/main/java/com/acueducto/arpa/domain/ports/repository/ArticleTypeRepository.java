package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeRepository {
    Optional<ArticleTypeEntity> update(Long id, ArticleTypeEntity type);

    List<ArticleTypeEntity> list();

    ArticleTypeEntity create(ArticleTypeEntity type);

    boolean delete(Long id);

    Optional<ArticleTypeEntity> findById(Long id);
}
