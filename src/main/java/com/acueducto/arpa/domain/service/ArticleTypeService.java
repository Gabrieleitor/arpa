package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {

    private final ArticleTypeRepository articleTypeRepository;

    public ArticleTypeService(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
    }

    public ArticleTypeEntity create(ArticleTypeEntity type) {
        return articleTypeRepository.create(type);
    }

    public List<ArticleTypeEntity> list() {
        return articleTypeRepository.list();
    }

    public Optional<ArticleTypeEntity> update(Long id, ArticleTypeEntity type) {
        return articleTypeRepository.update(id, type);
    }

    public boolean delete(Long id) {
        return articleTypeRepository.delete(id);
    }

}
