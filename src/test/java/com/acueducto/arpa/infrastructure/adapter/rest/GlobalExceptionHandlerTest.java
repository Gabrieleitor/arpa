package com.acueducto.arpa.infrastructure.adapter.rest;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleValidationExceptions_returnsBadRequestWithErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("object", "field1", "must not be null"),
                new FieldError("object", "field2", "must be positive")
        ));
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Map<String, String>> response = handler.handleValidationExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("must not be null", response.getBody().get("field1"));
        assertEquals("must be positive", response.getBody().get("field2"));
    }

    @Test
    void handleIllegalArgument_returnsBadRequest() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalArgument(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().get("error"));
    }

    @Test
    void handleIllegalState_returnsConflict() {
        IllegalStateException ex = new IllegalStateException("Conflict state");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalState(ex);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Conflict state", response.getBody().get("error"));
    }

    @Test
    void handleGeneral_returnsInternalServerError() {
        Exception ex = new Exception("Something went wrong");
        ResponseEntity<Map<String, String>> response = handler.handleGeneral(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().get("error").contains("Internal server"));
    }
} 