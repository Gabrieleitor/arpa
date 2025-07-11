package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.*;
import com.acueducto.arpa.domain.model.vo.*;
import com.acueducto.arpa.domain.ports.repository.*;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleRecordService {
    private final ArticleRecordRepository articleRecordRepository;
    private final IdentificationTypeRepository identificationTypeRepository;
    private final PersonTypeRepository personTypeRepository;
    private final ArticleTypeRepository articleTypeRepository;
    private final MakeRepository makeRepository;
    private static final Logger log = LoggerFactory.getLogger(ArticleRecordService.class);


    public ArticleRecordService(
            ArticleRecordRepository articleRecordRepository,
            IdentificationTypeRepository identificationTypeRepository,
            PersonTypeRepository personTypeRepository,
            ArticleTypeRepository articleTypeRepository,
            MakeRepository makeRepository) {
        this.articleRecordRepository = articleRecordRepository;
        this.identificationTypeRepository = identificationTypeRepository;
        this.personTypeRepository = personTypeRepository;
        this.articleTypeRepository = articleTypeRepository;
        this.makeRepository = makeRepository;
    }

    @Transactional
    public ArticleRecordDto registerEntry(ArticleRecordDto articleRecord) {
        log.info("Registering entry for article: name={}, makeId={}, serial={}", articleRecord.name(), articleRecord.make().id(),
                articleRecord.serial());
        try {
            IdentificationTypeDto identificationTypeDto = identificationTypeRepository.findById(articleRecord.identificationType().id())
                    .orElseThrow(() -> {
                        log.warn("Identification type not found: {}", articleRecord.identificationType().id());
                        return new IllegalArgumentException("Identification type not found");
                    });
            PersonTypeDto personTypeDto = personTypeRepository.findById(articleRecord.personType().id())
                    .orElseThrow(() -> {
                        log.warn("Person type not found: {}", articleRecord.personType().id());
                        return new IllegalArgumentException("Person type not found");
                    });
            ArticleTypeDto articleTypeDto = articleTypeRepository.findById(articleRecord.articleType().id())
                    .orElseThrow(() -> {
                        log.warn("Article type not found: {}", articleRecord.articleType().id());
                        return new IllegalArgumentException("Article type not found");
                    });
            MakeDto makeDto = makeRepository.findById(articleRecord.make().id())
                    .orElseThrow(() -> {
                        log.warn("Make not found: {}", articleRecord.make().id());
                        return new IllegalArgumentException("Make not found");
                    });
            ArticleRecordDto saved2 = ArticleRecordMapper.toDomain(
                    articleRecord, articleTypeDto, identificationTypeDto, personTypeDto, makeDto);

            ArticleRecordDto saved = articleRecordRepository.save(saved2);
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
        log.info("Registering exit for article: id={}", articleId);
        try {
            ArticleRecordDto article = articleRecordRepository.findById(articleId)
                    .orElseThrow(() -> {
                        log.warn("Article not found: {}", articleId);
                        return new IllegalArgumentException("Article not found");
                    });
            
            if (article.status() == ArticleStatus.EXIT) {
                log.warn("Article has already left: id={}", articleId);
                throw new IllegalStateException("Article has already left");
            }
            
            ArticleRecordDto exitArticle = ArticleRecordMapper.toExitStatus(article, LocalDateTime.now());
            ArticleRecordDto saved = articleRecordRepository.save(exitArticle);
            
            log.info("Article exit registered successfully: id={}", saved.id());
            return saved;
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Error registering article exit: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error registering article exit", e);
            throw e;
        }
    }

    @Transactional
    public List<ArticleRecordDto> list() {
        log.info("Listing all articles");
        try {
            List<ArticleRecordDto> articles = articleRecordRepository.findByStatusOrderByDateDesc();
            log.info("Found {} articles", articles.size());
            return articles;
        } catch (Exception e) {
            log.error("Error listing articles", e);
            throw e;
        }
    }
} 