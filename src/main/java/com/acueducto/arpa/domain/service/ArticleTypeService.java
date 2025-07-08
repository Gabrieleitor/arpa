package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {

    private final ArticleTypeRepository articleTypeRepository;

    public ArticleTypeService(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
    }

    public ArticleTypeDto create(ArticleTypeDto type) {
        return articleTypeRepository.create(type);
    }

    public List<ArticleTypeResponse> list() {
        return articleTypeRepository.list().stream()
                .map(ArticleTypeMapper::toResponse)
                .toList();
    }

    public Optional<ArticleTypeResponse> update(Long id, ArticleTypeDto type) {
        return articleTypeRepository.update(id, type)
                .map(ArticleTypeMapper::toResponse);
    }

    public boolean delete(Long id) {
        return articleTypeRepository.delete(id);
    }

}
