package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.PersonTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.PersonTypeResponse;
import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.service.PersonTypeService;
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

class PersonTypeHandlerTest {
    @Mock
    private PersonTypeService personTypeService;
    @InjectMocks
    private PersonTypeHandler handler;

    private PersonTypeDto personTypeDto;
    private PersonTypeRequest personTypeRequest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personTypeDto = new PersonTypeDto(1L, new Name("Empleado"));
        personTypeRequest = new PersonTypeRequest("Empleado");
    }

    @Test
    void list_success() {
        when(personTypeService.findAll()).thenReturn(List.of(personTypeDto));
        List<PersonTypeResponse> result = handler.list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Empleado", result.get(0).name());
    }

    @Test
    void create_success() {
        when(personTypeService.create(any())).thenReturn(personTypeDto);
        PersonTypeResponse result = handler.create(personTypeRequest);
        assertNotNull(result);
        assertEquals("Empleado", result.name());
    }

    @Test
    void update_success() {
        when(personTypeService.update(eq(1L), any())).thenReturn(Optional.of(personTypeDto));
        Optional<PersonTypeResponse> result = handler.update(1L, personTypeRequest);
        assertTrue(result.isPresent());
        assertEquals("Empleado", result.get().name());
    }

    @Test
    void update_notFound() {
        when(personTypeService.update(eq(1L), any())).thenReturn(Optional.empty());
        Optional<PersonTypeResponse> result = handler.update(1L, personTypeRequest);
        assertFalse(result.isPresent());
    }

    @Test
    void delete_success() {
        when(personTypeService.delete(1L)).thenReturn(true);
        assertTrue(handler.delete(1L));
    }

    @Test
    void delete_notFound() {
        when(personTypeService.delete(1L)).thenReturn(false);
        assertFalse(handler.delete(1L));
    }
} 