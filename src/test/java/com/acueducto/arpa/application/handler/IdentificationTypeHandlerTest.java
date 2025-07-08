package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;
import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.service.IdentificationTypeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.IdentificationTypeMapper;
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

class IdentificationTypeHandlerTest {
    @Mock
    private IdentificationTypeService identificationTypeService;
    @InjectMocks
    private IdentificationTypeHandler handler;

    private IdentificationTypeDto identificationTypeDto;
    private IdentificationTypeRequest identificationTypeRequest;
    private IdentificationTypeResponse identificationTypeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
        identificationTypeRequest = new IdentificationTypeRequest("CC");
        identificationTypeResponse = new IdentificationTypeResponse(1L, "CC");
    }

    @Test
    void list_success() {
        when(identificationTypeService.list()).thenReturn(List.of(identificationTypeDto));
        List<IdentificationTypeResponse> result = handler.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CC", result.get(0).name());
    }

    @Test
    void create_success() {
        when(identificationTypeService.create(any())).thenReturn(identificationTypeDto);
        IdentificationTypeResponse result = handler.create(identificationTypeRequest);
        assertNotNull(result);
        assertEquals("CC", result.name());
    }

    @Test
    void update_success() {
        when(identificationTypeService.update(eq(1L), any())).thenReturn(Optional.of(identificationTypeDto));
        Optional<IdentificationTypeResponse> result = handler.update(1L, identificationTypeRequest);
        assertTrue(result.isPresent());
        assertEquals("CC", result.get().name());
    }

    @Test
    void update_notFound() {
        when(identificationTypeService.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<IdentificationTypeResponse> result = handler.update(1L, identificationTypeRequest);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(identificationTypeService.delete(1L)).thenReturn(true);
        assertTrue(handler.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(identificationTypeService.delete(1L)).thenReturn(false);
        assertFalse(handler.delete(1L));
    }
} 