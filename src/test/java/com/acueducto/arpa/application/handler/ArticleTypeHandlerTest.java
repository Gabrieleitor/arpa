package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.service.ArticleTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ArticleTypeHandlerTest {
    @Mock
    private ArticleTypeService articleTypeService;
    @InjectMocks
    private ArticleTypeHandler handler;

    private ArticleTypeDto articleTypeDto;
    private ArticleTypeRequest articleTypeRequest;
    private ArticleTypeResponse articleTypeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
        articleTypeRequest = new ArticleTypeRequest("Laptop");
        articleTypeResponse = new ArticleTypeResponse(1L, "Laptop");
    }

    @Test
    void list_success() {
        when(articleTypeService.list()).thenReturn(List.of(articleTypeResponse));
        List<ArticleTypeResponse> result = handler.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).name());
    }

    @Test
    void create_success() {
        when(articleTypeService.create(any())).thenReturn(articleTypeDto);
        ArticleTypeResponse result = handler.create(articleTypeRequest);
        assertNotNull(result);
        assertEquals("Laptop", result.name());
    }

    @Test
    void update_success() {
        when(articleTypeService.update(eq(1L), any())).thenReturn(Optional.of(articleTypeResponse));
        Optional<ArticleTypeResponse> result = handler.update(1L, articleTypeRequest);
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().name());
    }

    @Test
    void update_notFound() {
        when(articleTypeService.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<ArticleTypeResponse> result = handler.update(1L, articleTypeRequest);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(articleTypeService.delete(1L)).thenReturn(true);
        assertTrue(handler.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(articleTypeService.delete(1L)).thenReturn(false);
        assertFalse(handler.delete(1L));
    }
} 