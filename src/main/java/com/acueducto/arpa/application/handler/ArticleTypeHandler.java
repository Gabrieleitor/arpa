package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.domain.service.ArticleTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeHandler {
    private final ArticleTypeService articleTypeService;

    public ArticleTypeHandler(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    public List<ArticleTypeResponse> list() {
        return articleTypeService.list();
    }

    public ArticleTypeResponse create(ArticleTypeRequest request) {
        ArticleTypeDto articleTypeDto = ArticleTypeMapper.toDomain(request);
        return ArticleTypeMapper.toResponse(articleTypeService.create(articleTypeDto));
    }

    public Optional<ArticleTypeResponse> update(Long id, ArticleTypeRequest request) {

        return articleTypeService.update(id, ArticleTypeMapper.toDomain(request));
    }

    public boolean delete(Long id) {
        return articleTypeService.delete(id);
    }
} 