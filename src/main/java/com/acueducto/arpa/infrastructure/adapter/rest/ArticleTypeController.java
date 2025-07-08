package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleTypeHandler;
import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article-types")
public class ArticleTypeController {
    private final ArticleTypeHandler handler;
    private static final Logger log = LoggerFactory.getLogger(ArticleTypeController.class);

    @Autowired
    public ArticleTypeController(ArticleTypeHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    public List<ArticleTypeResponse> list() {
        log.info("Received request to list article types");
        List<ArticleTypeResponse> list = handler.list();
        log.info("Returning {} article types", list.size());
        return list;
    }

    @PostMapping
    public ArticleTypeResponse create(@RequestBody ArticleTypeRequest type) {
        log.info("Received request to create article type: {}", type);
        ArticleTypeResponse response = handler.create(type);
        log.info("Article type created successfully: {}", response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleTypeResponse> update(@PathVariable Long id, @RequestBody ArticleTypeRequest type) {
        log.info("Received request to update article type: id={}", id);
        return handler.update(id, type)
                .map(updated -> {
                    log.info("Article type updated successfully: {}", updated);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    log.warn("Article type not found for update: id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Received request to delete article type: id={}", id);
        if (!handler.delete(id)) {
            log.warn("Article type not found for deletion: id={}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Article type deleted successfully: id={}", id);
        return ResponseEntity.noContent().build();
    }
} 