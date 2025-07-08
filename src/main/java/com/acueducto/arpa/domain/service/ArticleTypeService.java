package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {

    private static final Logger log = LoggerFactory.getLogger(ArticleTypeService.class);

    private final ArticleTypeRepository articleTypeRepository;

    public ArticleTypeService(ArticleTypeRepository articleTypeRepository) {
        this.articleTypeRepository = articleTypeRepository;
    }

    public ArticleTypeDto create(ArticleTypeDto type) {
        log.info("Creating article type: {}", type);
        try {
            ArticleTypeDto created = articleTypeRepository.create(type);
            log.info("Article type created successfully: {}", created);
            return created;
        } catch (Exception e) {
            log.error("Error creating article type", e);
            throw e;
        }
    }

    public List<ArticleTypeResponse> list() {
        log.info("Listing all article types");
        try {
            List<ArticleTypeResponse> list = articleTypeRepository.list().stream()
                    .map(ArticleTypeMapper::toResponse)
                    .toList();
            log.info("Found {} article types", list.size());
            return list;
        } catch (Exception e) {
            log.error("Error listing article types", e);
            throw e;
        }
    }

    public Optional<ArticleTypeResponse> update(Long id, ArticleTypeDto type) {
        log.info("Updating article type: id={}", id);
        try {
            Optional<ArticleTypeResponse> updated = articleTypeRepository.update(id, type)
                    .map(ArticleTypeMapper::toResponse);
            if (updated.isPresent()) {
                log.info("Article type updated successfully: id={}", id);
            } else {
                log.warn("Article type not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Error updating article type: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Deleting article type: id={}", id);
        try {
            boolean deleted = articleTypeRepository.delete(id);
            if (deleted) {
                log.info("Article type deleted successfully: id={}", id);
            } else {
                log.warn("Article type not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Error deleting article type: id={}", id, e);
            throw e;
        }
    }

}
