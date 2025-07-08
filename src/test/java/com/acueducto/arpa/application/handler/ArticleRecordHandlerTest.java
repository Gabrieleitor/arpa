package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleRecordDto;
import com.acueducto.arpa.domain.model.vo.ArticleStatus;
import com.acueducto.arpa.domain.model.vo.Comment;
import com.acueducto.arpa.domain.model.vo.Make;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.service.ArticleRecordService;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ArticleRecordHandlerTest {
    @Mock
    private ArticleRecordService articleRecordService;
    @InjectMocks
    private ArticleRecordHandler handler;

    private ArticleRecordDto articleRecordDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArticleTypeDto articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
        IdentificationTypeDto identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
        PersonTypeDto personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
        articleRecordDto = new ArticleRecordDto(1L, new Name("HP"), new Serial("12345"), ArticleStatus.ENTRY,
                articleTypeDto, identificationTypeDto, personTypeDto, "987654", new Make("HP Inc."), new Comment("Test"), LocalDateTime.now(), null);
    }

    @Test
    void registerEntry_success() {
        when(articleRecordService.registerEntry(anyLong(), anyLong(), anyLong(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(articleRecordDto);
        ArticleRecordResponse response = handler.registerEntry(1L, 1L, 1L, "HP", "HP Inc.", "12345", "Test", "987654");
        assertNotNull(response);
        assertEquals("HP", response.name());
    }

    @Test
    void registerEntry_error() {
        when(articleRecordService.registerEntry(anyLong(), anyLong(), anyLong(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("Identification type not found"));
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                handler.registerEntry(1L, 1L, 1L, "HP", "HP Inc.", "12345", "Test", "987654")
        );
        assertEquals("Identification type not found", ex.getMessage());
    }

    @Test
    void registerExit_success() {
        when(articleRecordService.registerExit(anyLong())).thenReturn(articleRecordDto);
        ArticleRecordResponse response = handler.registerExit(1L);
        assertNotNull(response);
        assertEquals("HP", response.name());
    }

    @Test
    void registerExit_error() {
        when(articleRecordService.registerExit(anyLong())).thenThrow(new IllegalArgumentException("Article not found"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> handler.registerExit(1L));
        assertEquals("Article not found", ex.getMessage());
    }
} 