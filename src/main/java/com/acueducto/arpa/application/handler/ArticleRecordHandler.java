package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.domain.service.ArticleRecordService;
import com.acueducto.arpa.infrastructure.adapter.rest.dto.ArticleRecordDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleRecordHandler {

    private ArticleRecordService articleRecordService;

    @Autowired
    public ArticleRecordHandler(ArticleRecordService articleRecordService) {
        this.articleRecordService = articleRecordService;
    }

    public ArticleRecordDto registerEntry(Long identificationTypeId, Long personTypeId, Long articleTypeId, String name, String make, String serial, String comment) {
        return articleRecordService.registerEntry(identificationTypeId, personTypeId, articleTypeId, name, make, serial, comment);
    }

    public ArticleRecordDto registerExit(Long articleId) {
        ArticleRecordEntity entity = articleRecordService.registerExit(articleId);
        return ArticleRecordMapper.toDto(entity);
    }

}
