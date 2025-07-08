package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;

import java.util.List;
import java.util.Optional;

public interface ArticleTypeRepository {
    Optional<ArticleTypeDto> update(Long id, ArticleTypeDto type);

    List<ArticleTypeDto> list();

    ArticleTypeDto create(ArticleTypeDto type);

    boolean delete(Long id);

    Optional<ArticleTypeDto> findById(Long id);
}
