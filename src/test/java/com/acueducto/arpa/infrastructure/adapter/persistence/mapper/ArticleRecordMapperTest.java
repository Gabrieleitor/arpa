package com.acueducto.arpa.infrastructure.adapter.persistence.mapper;

import com.acueducto.arpa.domain.model.dtos.*;
import com.acueducto.arpa.domain.model.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ArticleRecordMapperTest {

    private ArticleRecordDto articleRecordDto;
    private ArticleTypeDto articleTypeDto;
    private IdentificationTypeDto identificationTypeDto;
    private PersonTypeDto personTypeDto;
    private MakeDto makeDto;

    @BeforeEach
    void setUp() {
        articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
        identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
        personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
        makeDto = new MakeDto(1L, new Name("HP Inc."));
        
        articleRecordDto = new ArticleRecordDto(
                1L,
                new Name("HP"),
                new Name("Perez"),
                new Serial("12345"),
                ArticleStatus.ENTRY,
                articleTypeDto,
                identificationTypeDto,
                personTypeDto,
                "987654",
                makeDto,
                new Comment("Test"),
                LocalDateTime.now(),
                null
        );
    }

    @Test
    void toExitStatus_shouldCreateCorrectExitArticle() {
        LocalDateTime exitDate = LocalDateTime.now();
        
        ArticleRecordDto result = ArticleRecordMapper.toExitStatus(articleRecordDto, exitDate);
        
        assertNotNull(result);
        assertEquals(articleRecordDto.id(), result.id());
        assertEquals(articleRecordDto.name(), result.name());
        assertEquals(articleRecordDto.lastName(), result.lastName());
        assertEquals(articleRecordDto.serial(), result.serial());
        assertEquals(ArticleStatus.EXIT, result.status());
        assertEquals(articleRecordDto.articleType(), result.articleType());
        assertEquals(articleRecordDto.identificationType(), result.identificationType());
        assertEquals(articleRecordDto.personType(), result.personType());
        assertEquals(articleRecordDto.identificationNumber(), result.identificationNumber());
        assertEquals(articleRecordDto.make(), result.make());
        assertEquals(articleRecordDto.comment(), result.comment());
        assertEquals(articleRecordDto.entryDate(), result.entryDate());
        assertEquals(exitDate, result.exitDate());
    }

    @Test
    void toExitStatus_shouldPreserveOriginalData() {
        LocalDateTime originalEntryDate = LocalDateTime.of(2024, 1, 1, 10, 0);
        ArticleRecordDto original = new ArticleRecordDto(
                1L,
                new Name("Dell"),
                new Name("Garcia"),
                new Serial("67890"),
                ArticleStatus.ENTRY,
                articleTypeDto,
                identificationTypeDto,
                personTypeDto,
                "123456",
                makeDto,
                new Comment("Original comment"),
                originalEntryDate,
                null
        );
        
        LocalDateTime exitDate = LocalDateTime.now();
        ArticleRecordDto result = ArticleRecordMapper.toExitStatus(original, exitDate);
        
        assertEquals("Dell", result.name().value());
        assertEquals("Garcia", result.lastName().value());
        assertEquals("67890", result.serial().value());
        assertEquals("Original comment", result.comment().value());
        assertEquals("123456", result.identificationNumber());
        assertEquals(originalEntryDate, result.entryDate());
        assertEquals(exitDate, result.exitDate());
    }
} 