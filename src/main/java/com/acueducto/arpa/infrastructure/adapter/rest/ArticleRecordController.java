package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleRecordHandler;
import com.acueducto.arpa.infrastructure.adapter.rest.dto.ArticleRecordDto;
import com.acueducto.arpa.infrastructure.adapter.rest.dto.ArticleRecordEntryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/articles")
public class ArticleRecordController {
    private final ArticleRecordHandler articleRecordHandler;

    @Autowired
    public ArticleRecordController(ArticleRecordHandler articleRecordHandler) {
        this.articleRecordHandler = articleRecordHandler;
    }

    @PostMapping("/entry")
    public ResponseEntity<ArticleRecordDto> registerEntry(@Valid @RequestBody ArticleRecordEntryRequest request) {
        ArticleRecordDto article = articleRecordHandler.registerEntry(
                request.getIdentificationTypeId(),
                request.getPersonTypeId(),
                request.getArticleTypeId(),
                request.getName(),
                request.getMake(),
                request.getSerial(),
                request.getComment()
        );
        return ResponseEntity.ok(article);
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ArticleRecordDto> registerExit(@PathVariable Long id) {
        ArticleRecordDto article = articleRecordHandler.registerExit(id);
        return ResponseEntity.ok(article);
    }
} 