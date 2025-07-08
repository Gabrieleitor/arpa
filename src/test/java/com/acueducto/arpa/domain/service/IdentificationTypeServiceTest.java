package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
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

class IdentificationTypeServiceTest {
    @Mock
    private IdentificationTypeRepository identificationTypeRepository;
    @InjectMocks
    private IdentificationTypeService service;

    private IdentificationTypeDto identificationTypeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        identificationTypeDto = new IdentificationTypeDto(1L, new Name("CC"));
    }

    @Test
    void list_success() {
        when(identificationTypeRepository.list()).thenReturn(List.of(identificationTypeDto));
        List<IdentificationTypeDto> result = service.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CC", result.get(0).name().value());
    }

    @Test
    void create_success() {
        when(identificationTypeRepository.create(any())).thenReturn(identificationTypeDto);
        IdentificationTypeDto result = service.create(identificationTypeDto);
        assertNotNull(result);
        assertEquals("CC", result.name().value());
    }

    @Test
    void update_success() {
        when(identificationTypeRepository.update(eq(1L), any())).thenReturn(Optional.of(identificationTypeDto));
        Optional<IdentificationTypeDto> result = service.update(1L, identificationTypeDto);
        assertTrue(result.isPresent());
        assertEquals("CC", result.get().name().value());
    }

    @Test
    void update_notFound() {
        when(identificationTypeRepository.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<IdentificationTypeDto> result = service.update(1L, identificationTypeDto);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(identificationTypeRepository.delete(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(identificationTypeRepository.delete(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }
} 