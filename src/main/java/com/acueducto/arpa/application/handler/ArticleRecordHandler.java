package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.service.ArticleRecordService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRecordHandler {

    private static final Logger log = LoggerFactory.getLogger(ArticleRecordHandler.class);
    private final ArticleRecordService articleRecordService;

    @Autowired
    public ArticleRecordHandler(ArticleRecordService articleRecordService) {
        this.articleRecordService = articleRecordService;
    }

    public ArticleRecordResponse registerEntry(ArticleRecordRequest request) {
        log.info("Handler: Registering entry for article: name={}, makeId={}, serial={}", request.name(), request.makeId(), request.serialNumber());
        try {
            ArticleRecordDto articleRecordDto = ArticleRecordMapper.toDomain(request);
            ArticleRecordResponse response = ArticleRecordMapper.toResponse(articleRecordService.registerEntry(articleRecordDto));
            log.info("Handler: Article entry registered successfully: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Handler: Error registering article entry", e);
            throw e;
        }
    }

    public ArticleRecordResponse registerExit(Long articleId) {
        try {
            ArticleRecordDto articleRecordDto = articleRecordService.registerExit(articleId);
            return ArticleRecordMapper.toResponse(articleRecordDto);
        } catch (Exception e) {
            log.error("Handler: Error registering article exit", e);
            throw e;
        }
    }

    public List<ArticleRecordResponse> list() {
        log.info("Handler: Listing all articles");
        try {
            List<ArticleRecordDto> articleRecords = articleRecordService.list();
            return ArticleRecordMapper.toResponseList(articleRecords);
        } catch (Exception e) {
            log.error("Handler: Error listing articles", e);
            throw e;
        }
    }

}
