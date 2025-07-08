package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleRecordHandler;
import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
public class ArticleRecordController {
    private static final Logger log = LoggerFactory.getLogger(ArticleRecordController.class);
    private final ArticleRecordHandler articleRecordHandler;

    @Autowired
    public ArticleRecordController(ArticleRecordHandler articleRecordHandler) {
        this.articleRecordHandler = articleRecordHandler;
    }

    @PostMapping("/entry")
    public ResponseEntity<ArticleRecordResponse> registerEntry(@Valid @RequestBody ArticleRecordRequest request) {
        log.info("Received request to register article entry: {}", request);
        ArticleRecordResponse article = articleRecordHandler.registerEntry(
                request.identificationTypeId(),
                request.personTypeId(),
                request.articleTypeId(),
                request.name(),
                request.makerName(),
                request.serialNumber(),
                request.comment(),
                request.identificationNumber()
        );
        log.info("Article entry registered successfully: {}", article);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/{id}/exit")
    public ResponseEntity<ArticleRecordResponse> registerExit(@PathVariable Long id) {
        log.info("Received request to register article exit: id={}", id);
        ArticleRecordResponse article = articleRecordHandler.registerExit(id);
        log.info("Article exit registered successfully: {}", article);
        return ResponseEntity.ok(article);
    }
} 