package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.vo.Comment;
import com.acueducto.arpa.domain.model.vo.Make;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.ports.repository.ArticleRecordRepository;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity.ArticleStatus;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleTypeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import com.acueducto.arpa.infrastructure.adapter.rest.dto.ArticleRecordDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ArticleRecordService {
    private final ArticleRecordRepository articleRecordRepository;
    private final IdentificationTypeRepository identificationTypeRepository;
    private final PersonTypeRepository personTypeRepository;
    private final ArticleTypeRepository articleTypeRepository;


    public ArticleRecordService(
            ArticleRecordRepository articleRecordRepository,
            IdentificationTypeRepository identificationTypeRepository,
            PersonTypeRepository personTypeRepository,
            ArticleTypeRepository articleTypeRepository) {
        this.articleRecordRepository = articleRecordRepository;
        this.identificationTypeRepository = identificationTypeRepository;
        this.personTypeRepository = personTypeRepository;
        this.articleTypeRepository = articleTypeRepository;
    }

    @Transactional
    public ArticleRecordDto registerEntry(Long identificationTypeId, Long personTypeId, Long articleTypeId,
                                          String name, String make, String serial, String comment) {
        IdentificationTypeEntity identificationTypeEntity = identificationTypeRepository.findById(identificationTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Identification type not found"));
        PersonTypeEntity personTypeEntity = personTypeRepository.findById(personTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Person type not found"));
        ArticleTypeEntity articleTypeEntity = articleTypeRepository.findById(articleTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Article type not found"));

        ArticleRecordEntity article = new ArticleRecordEntity();
        article.setName(new Name(name));
        article.setIdentificationType(identificationTypeEntity);
        article.setPersonType(personTypeEntity);
        article.setArticleType(articleTypeEntity);
        article.setMake(new Make(make));
        article.setSerial(new Serial(serial));
        article.setComment(new Comment(comment));
        article.setStatus(ArticleStatus.ENTRY);
        article.setEntryDate(LocalDateTime.now());
        article.setExitDate(null);
        return ArticleRecordMapper.toDto(articleRecordRepository.save(article));
    }

    @Transactional
    public ArticleRecordEntity registerExit(Long articleId) {
        ArticleRecordEntity article = articleRecordRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
        if (article.getStatus() == ArticleStatus.EXIT) {
            throw new IllegalStateException("Article has already left");
        }
        article.setStatus(ArticleStatus.EXIT);
        article.setExitDate(LocalDateTime.now());
        return articleRecordRepository.save(article);
    }
} 