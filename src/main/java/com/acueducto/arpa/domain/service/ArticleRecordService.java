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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ArticleRecordService {
    private final ArticleRecordRepository articleRecordRepository;
    private final IdentificationTypeRepository identificationTypeRepository;
    private final PersonTypeRepository personTypeRepository;
    private final ArticleTypeRepository articleTypeRepository;
    private static final Logger log = LoggerFactory.getLogger(ArticleRecordService.class);


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
        log.info("Registering entry for article: name={}, make={}, serial={}", name, make, serial);
        try {
            IdentificationTypeDto IdentificationTypeDto = identificationTypeRepository.findById(identificationTypeId)
                    .orElseThrow(() -> {
                        log.warn("Identification type not found: {}", identificationTypeId);
                        return new IllegalArgumentException("Identification type not found");
                    });
            PersonTypeDto personTypeDto = personTypeRepository.findById(personTypeId)
                    .orElseThrow(() -> {
                        log.warn("Person type not found: {}", personTypeId);
                        return new IllegalArgumentException("Person type not found");
                    });
            ArticleTypeDto articleTypeDto = articleTypeRepository.findById(articleTypeId)
                    .orElseThrow(() -> {
                        log.warn("Article type not found: {}", articleTypeId);
                        return new IllegalArgumentException("Article type not found");
                    });
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
                    LocalDateTime.now(),
                    null
            );
            ArticleRecordDto saved = articleRecordRepository.save(articleRecordDto);
            log.info("Article entry registered successfully: id={}", saved.id());
            return saved;
        } catch (IllegalArgumentException e) {
            log.error("Error registering article entry: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error registering article entry", e);
            throw e;
        }
    }

    @Transactional
    public ArticleRecordDto registerExit(Long articleId) {
        try {
            ArticleRecordDto article = articleRecordRepository.findById(articleId)
                    .orElseThrow(() -> new IllegalArgumentException("Article not found"));
            if (article.status() == ArticleStatus.EXIT) {
                throw new IllegalStateException("Article has already left");
            }
            article = new ArticleRecordDto(
                    article.id(),
                    article.name(),
                    article.serial(),
                    ArticleStatus.EXIT,
                    article.articleType(),
                    article.identificationType(),
                    article.personType(),
                    article.identificationNumber(),
                    article.make(),
                    article.comment(),
                    article.entryDate(),
                    LocalDateTime.now()
            );
            ArticleRecordDto saved = articleRecordRepository.save(article);
            return saved;
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Error registering article exit: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error registering article exit", e);
            throw e;
        }
    }
} 