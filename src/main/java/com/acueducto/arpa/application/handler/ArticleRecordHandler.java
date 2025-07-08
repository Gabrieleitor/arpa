package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.service.ArticleRecordService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleRecordHandler {

    private static final Logger log = LoggerFactory.getLogger(ArticleRecordHandler.class);
    private ArticleRecordService articleRecordService;

    @Autowired
    public ArticleRecordHandler(ArticleRecordService articleRecordService) {
        this.articleRecordService = articleRecordService;
    }

    public ArticleRecordResponse registerEntry(Long identificationTypeId, Long personTypeId, Long articleTypeId,
                                               String name, String make, String serial, String comment, String identificationNumber) {
        try {
            ArticleRecordResponse response = ArticleRecordMapper.toResponse(articleRecordService.registerEntry(identificationTypeId, personTypeId,
                    articleTypeId, name, make, serial, comment, identificationNumber));
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

}
