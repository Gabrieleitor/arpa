package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.acueducto.arpa.domain.model.dtos.*;
import com.acueducto.arpa.domain.model.vo.ArticleStatus;
import com.acueducto.arpa.domain.model.vo.Comment;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.service.ArticleRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

class ArticleRecordHandlerTest {
    @Mock
    private ArticleRecordService articleRecordService;
    @InjectMocks
    private ArticleRecordHandler handler;

    private ArticleRecordRequest request;
    private ArticleRecordDto articleRecordDto;
    private ArticleRecordResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArticleTypeDto articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
        IdentificationTypeDto identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
        PersonTypeDto personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
        MakeDto makeDto = new MakeDto(1L, new Name("HP Inc."));
        articleRecordDto = new ArticleRecordDto(1L, new Name("HP"), new Name("Perez"), new Serial("12345"), ArticleStatus.ENTRY,
                articleTypeDto, identificationTypeDto, personTypeDto, "987654", makeDto, new Comment("Test"), LocalDateTime.now(), null);
        request = new ArticleRecordRequest("Carlos", "Perez", 1L, 1L, 1L, "Test", 1L, "987654", "12345");
        response = new ArticleRecordResponse(1L, "Carlos", "Perez", "12345", "ENTRY", "Laptop", "CC", "Empleado", "Test", "2024-01-01T10:00:00", "HP Inc.", "987654");
    }

    @Test
    void registerEntry_success() {
        when(articleRecordService.registerEntry(any(ArticleRecordDto.class))).thenReturn(articleRecordDto);
        ArticleRecordResponse result = handler.registerEntry(request);
        assertNotNull(result);
        assertEquals("HP", result.name());
    }

    @Test
    void registerEntry_error() {
        when(articleRecordService.registerEntry(any(ArticleRecordDto.class))).thenThrow(new IllegalArgumentException("Identification type not found"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> handler.registerEntry(request));
        assertEquals("Identification type not found", ex.getMessage());
    }

    @Test
    void registerExit_success() {
        when(articleRecordService.registerExit(anyLong())).thenReturn(articleRecordDto);
        ArticleRecordResponse result = handler.registerExit(1L);
        assertNotNull(result);
        assertEquals("HP", result.name());
    }

    @Test
    void registerExit_error() {
        when(articleRecordService.registerExit(anyLong())).thenThrow(new IllegalArgumentException("Article not found"));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> handler.registerExit(1L));
        assertEquals("Article not found", ex.getMessage());
    }

} 