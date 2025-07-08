package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.model.vo.*;
import com.acueducto.arpa.domain.ports.repository.ArticleRecordRepository;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.ArticleRecordEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
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
                                          String name, String make, String serial, String comment, String identificationNumber) {
        IdentificationTypeDto IdentificationTypeDto = identificationTypeRepository.findById(identificationTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Identification type not found"));
        PersonTypeDto personTypeDto = personTypeRepository.findById(personTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Person type not found"));
        ArticleTypeDto articleTypeDto = articleTypeRepository.findById(articleTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Article type not found"));
        ArticleRecordDto articleRecordDto = new ArticleRecordDto(
                null,
                new Name(name),
                new Serial(serial),
                ArticleStatus.ENTRY,
                articleTypeDto,
                IdentificationTypeDto,
                personTypeDto,
                identificationNumber,
                new Make(make),
                new Comment(comment),
                LocalDateTime.now()
        );
        return articleRecordRepository.save(articleRecordDto);
    }

    @Transactional
    public ArticleRecordDto registerExit(Long articleId) {
        ArticleRecordDto article = articleRecordRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));
        if (article.status() == ArticleStatus.EXIT) {
            throw new IllegalStateException("Article has already left");
        }
        ArticleRecordEntity entity = ArticleRecordMapper.toEntity(article);
        entity.setStatus(ArticleRecordEntity.ArticleStatus.EXIT);
        entity.setExitDate(LocalDateTime.now());
        return articleRecordRepository.save(article);
    }
} 