package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.MakeRequest;
import com.acueducto.arpa.application.handler.dtos.response.MakeResponse;
import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.service.MakeService;
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
import static org.mockito.Mockito.when;

class MakeHandlerTest {
    @Mock
    private MakeService makeService;
    @InjectMocks
    private MakeHandler handler;

    private MakeDto makeDto;
    private MakeRequest makeRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        makeDto = new MakeDto(1L, new Name("HP Inc."));
        makeRequest = new MakeRequest("HP Inc.");
    }

    @Test
    void list_success() {
        when(makeService.findAll()).thenReturn(List.of(makeDto));
        List<MakeResponse> result = handler.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("HP Inc.", result.get(0).name());
    }

    @Test
    void create_success() {
        when(makeService.create(any())).thenReturn(makeDto);
        MakeResponse result = handler.create(makeRequest);
        assertNotNull(result);
        assertEquals("HP Inc.", result.name());
    }

    @Test
    void update_success() {
        when(makeService.update(eq(1L), any())).thenReturn(Optional.of(makeDto));
        Optional<MakeResponse> result = handler.update(1L, makeRequest);
        assertTrue(result.isPresent());
        assertEquals("HP Inc.", result.get().name());
    }

    @Test
    void update_notFound() {
        when(makeService.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<MakeResponse> result = handler.update(1L, makeRequest);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(makeService.delete(1L)).thenReturn(true);
        assertTrue(handler.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(makeService.delete(1L)).thenReturn(false);
        assertFalse(handler.delete(1L));
    }
} 