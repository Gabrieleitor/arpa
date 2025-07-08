package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.model.vo.ArticleStatus;
import com.acueducto.arpa.domain.model.vo.Comment;
import com.acueducto.arpa.domain.model.vo.Make;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.ports.repository.ArticleRecordRepository;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticleRecordServiceTest {
    @Mock
    private ArticleRecordRepository articleRecordRepository;
    @Mock
    private IdentificationTypeRepository identificationTypeRepository;
    @Mock
    private PersonTypeRepository personTypeRepository;
    @Mock
    private ArticleTypeRepository articleTypeRepository;

    @InjectMocks
    private ArticleRecordService service;

    private IdentificationTypeDto identificationTypeDto;
    private PersonTypeDto personTypeDto;
    private ArticleTypeDto articleTypeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
        personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
        articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
    }

    @Test
    void registerEntry_success() {
        when(identificationTypeRepository.findById(1L)).thenReturn(Optional.of(identificationTypeDto));
        when(personTypeRepository.findById(1L)).thenReturn(Optional.of(personTypeDto));
        when(articleTypeRepository.findById(1L)).thenReturn(Optional.of(articleTypeDto));
        when(articleRecordRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ArticleRecordDto result = service.registerEntry(1L, 1L, 1L, "HP", "HP Inc.", "12345", "Test", "987654");
        assertNotNull(result);
        assertEquals(ArticleStatus.ENTRY, result.status());
        assertEquals("HP", result.name().value());
    }

    @Test
    void registerEntry_identificationTypeNotFound() {
        when(identificationTypeRepository.findById(1L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                service.registerEntry(1L, 1L, 1L, "HP", "HP Inc.", "12345", "Test", "987654")
        );
        assertEquals("Identification type not found", ex.getMessage());
    }

    @Test
    void registerExit_success() {
        ArticleRecordDto existing = new ArticleRecordDto(1L, new Name("HP"), new Serial("12345"), ArticleStatus.ENTRY,
                articleTypeDto, identificationTypeDto, personTypeDto, "987654", new Make("HP Inc."), new Comment("Test"), LocalDateTime.now(), null);
        when(articleRecordRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(articleRecordRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ArticleRecordDto result = service.registerExit(1L);
        assertNotNull(result);
        assertEquals(ArticleStatus.EXIT, result.status());
    }

    @Test
    void registerExit_articleNotFound() {
        when(articleRecordRepository.findById(1L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.registerExit(1L));
        assertEquals("Article not found", ex.getMessage());
    }

    @Test
    void registerExit_alreadyExited() {
        ArticleRecordDto existing = new ArticleRecordDto(1L, new Name("HP"), new Serial("12345"), ArticleStatus.EXIT,
                articleTypeDto, identificationTypeDto, personTypeDto, "987654", new Make("HP Inc."), new Comment("Test"), LocalDateTime.now(), LocalDateTime.now());
        when(articleRecordRepository.findById(1L)).thenReturn(Optional.of(existing));
        Exception ex = assertThrows(IllegalStateException.class, () -> service.registerExit(1L));
        assertEquals("Article has already left", ex.getMessage());
    }
} 