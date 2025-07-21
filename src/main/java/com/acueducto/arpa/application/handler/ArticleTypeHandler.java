package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.service.ArticleTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeHandler {
    private static final Logger log = LoggerFactory.getLogger(ArticleTypeHandler.class);
    private final ArticleTypeService articleTypeService;

    public ArticleTypeHandler(ArticleTypeService articleTypeService) {
        this.articleTypeService = articleTypeService;
    }

    public List<ArticleTypeResponse> list() {
        try {
            List<ArticleTypeResponse> list = articleTypeService.list();
            log.info("Handler: Found {} article types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Handler: Error listing article types", e);
            throw e;
        }
    }

    public ArticleTypeResponse create(ArticleTypeRequest request) {
        log.info("Handler: Creating article type: {}", request);
        try {
            ArticleTypeDto articleTypeDto = ArticleTypeMapper.toDomain(request);
            ArticleTypeResponse response = ArticleTypeMapper.toResponse(articleTypeService.create(articleTypeDto));
            log.info("Handler: Article type created successfully: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Handler: Error creating article type", e);
            throw e;
        }
    }

    public Optional<ArticleTypeResponse> update(Long id, ArticleTypeRequest request) {
        log.info("Handler: Updating article type: id={}", id);
        try {
            Optional<ArticleTypeResponse> updated = articleTypeService.update(id, ArticleTypeMapper.toDomain(request));
            if (updated.isPresent()) {
                log.info("Handler: Article type updated successfully: id={}", id);
            } else {
                log.warn("Handler: Article type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Handler: Error updating article type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Handler: Deleting article type: id={}", id);
        try {
            boolean deleted = articleTypeService.delete(id);
            if (deleted) {
                log.info("Handler: Article type deleted successfully: id={}", id);
            } else {
                log.warn("Handler: Article type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Handler: Error deleting article type: id={}", id, e);
            throw e;
        }
    }
} 