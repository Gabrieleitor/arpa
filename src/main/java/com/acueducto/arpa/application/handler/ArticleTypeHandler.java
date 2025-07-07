package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.service.ArticleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeHandler {
    private final ArticleTypeService articleTypeService;

    public ArticleTypeHandler(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    public List<ArticleTypeEntity> list() {
        return articleTypeService.list();
    }

    public ArticleTypeEntity create(ArticleTypeEntity type) {
        return articleTypeService.create(type);
    }

    public Optional<ArticleTypeEntity> update(Long id, ArticleTypeEntity type) {
        return articleTypeService.update(id, type);
    }

    public boolean delete(Long id) {
        return articleTypeService.delete(id);
    }
} 