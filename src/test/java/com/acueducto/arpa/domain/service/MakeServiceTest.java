package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.ports.repository.MakeRepository;
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

class MakeServiceTest {
    @Mock
    private MakeRepository makeRepository;
    @InjectMocks
    private MakeService service;

    private MakeDto makeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        makeDto = new MakeDto(1L, new Name("HP Inc."));
    }

    @Test
    void findAll_success() {
        when(makeRepository.list()).thenReturn(List.of(makeDto));
        List<MakeDto> result = service.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("HP Inc.", result.get(0).name().value());
    }

    @Test
    void create_success() {
        when(makeRepository.create(any())).thenReturn(makeDto);
        MakeDto result = service.create(makeDto);
        assertNotNull(result);
        assertEquals("HP Inc.", result.name().value());
    }

    @Test
    void update_success() {
        when(makeRepository.update(eq(1L), any())).thenReturn(Optional.of(makeDto));
        Optional<MakeDto> result = service.update(1L, makeDto);
        assertTrue(result.isPresent());
        assertEquals("HP Inc.", result.get().name().value());
    }

    @Test
    void update_notFound() {
        when(makeRepository.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<MakeDto> result = service.update(1L, makeDto);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(makeRepository.delete(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(makeRepository.delete(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }
} 