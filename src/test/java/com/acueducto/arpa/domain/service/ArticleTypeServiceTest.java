package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.ArticleTypeDto;
import com.acueducto.arpa.domain.ports.repository.ArticleTypeRepository;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.ArticleTypeMapper;
import com.acueducto.arpa.domain.model.vo.Name;
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

class ArticleTypeServiceTest {
    @Mock
    private ArticleTypeRepository articleTypeRepository;
    @InjectMocks
    private ArticleTypeService service;

    private ArticleTypeDto articleTypeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        articleTypeDto = new ArticleTypeDto(1L, new Name("Laptop"));
    }

    @Test
    void create_success() {
        when(articleTypeRepository.create(any())).thenReturn(articleTypeDto);
        ArticleTypeDto result = service.create(articleTypeDto);
        assertNotNull(result);
        assertEquals("Laptop", result.name().value());
    }

    @Test
    void list_success() {
        when(articleTypeRepository.list()).thenReturn(List.of(articleTypeDto));
        List<ArticleTypeResponse> result = service.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).name());
    }

    @Test
    void update_success() {
        when(articleTypeRepository.update(eq(1L), any())).thenReturn(Optional.of(articleTypeDto));
        Optional<ArticleTypeResponse> result = service.update(1L, articleTypeDto);
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().name());
    }

    @Test
    void update_notFound() {
        when(articleTypeRepository.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<ArticleTypeResponse> result = service.update(1L, articleTypeDto);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(articleTypeRepository.delete(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(articleTypeRepository.delete(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }
} 