package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleTypeHandler;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/article-types")
public class ArticleTypeController {
    private final ArticleTypeHandler handler;

    @Autowired
    public ArticleTypeController(ArticleTypeHandler handler) {
        this.handler = handler;
    }

    @GetMapping
    public List<ArticleTypeEntity> list() {
        return handler.list();
    }

    @PostMapping
    public ArticleTypeEntity create(@RequestBody ArticleTypeEntity type) {
        return handler.create(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleTypeEntity> update(@PathVariable Long id, @RequestBody ArticleTypeEntity type) {
        return handler.update(id, type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!handler.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
} 