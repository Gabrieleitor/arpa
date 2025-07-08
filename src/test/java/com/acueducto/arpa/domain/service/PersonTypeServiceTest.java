package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
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

class PersonTypeServiceTest {
    @Mock
    private PersonTypeRepository personTypeRepository;
    @InjectMocks
    private PersonTypeService service;

    private PersonTypeDto personTypeDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
    }

    @Test
    void findAll_success() {
        when(personTypeRepository.list()).thenReturn(List.of(personTypeDto));
        List<PersonTypeDto> result = service.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Empleado", result.get(0).name().value());
    }

    @Test
    void create_success() {
        when(personTypeRepository.create(any())).thenReturn(personTypeDto);
        PersonTypeDto result = service.create(personTypeDto);
        assertNotNull(result);
        assertEquals("Empleado", result.name().value());
    }

    @Test
    void update_success() {
        when(personTypeRepository.update(eq(1L), any())).thenReturn(Optional.of(personTypeDto));
        Optional<PersonTypeDto> result = service.update(1L, personTypeDto);
        assertTrue(result.isPresent());
        assertEquals("Empleado", result.get().name().value());
    }

    @Test
    void update_notFound() {
        when(personTypeRepository.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<PersonTypeDto> result = service.update(1L, personTypeDto);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(personTypeRepository.delete(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(personTypeRepository.delete(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }
} 