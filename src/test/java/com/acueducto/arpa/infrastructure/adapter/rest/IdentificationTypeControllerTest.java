package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.IdentificationTypeHandler;
import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IdentificationTypeController.class)
class IdentificationTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private IdentificationTypeHandler handler;
    @Autowired
    private ObjectMapper objectMapper;

    private IdentificationTypeRequest request;
    private IdentificationTypeResponse response;

    @BeforeEach
    void setUp() {
        request = new IdentificationTypeRequest("CC");
        response = new IdentificationTypeResponse(1L, "CC");
    }

    @Test
    void list_success() throws Exception {
        when(handler.list()).thenReturn(List.of(response));
        mockMvc.perform(get("/api/identification-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CC"));
    }

    @Test
    void create_success() throws Exception {
        when(handler.create(any())).thenReturn(response);
        mockMvc.perform(post("/api/identification-types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("CC"));
    }

    @Test
    void update_success() throws Exception {
        when(handler.update(eq(1L), any())).thenReturn(Optional.of(response));
        mockMvc.perform(put("/api/identification-types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("CC"));
    }

    @Test
    void update_notFound() throws Exception {
        when(handler.update(eq(1L), any())).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/identification-types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_success() throws Exception {
        when(handler.delete(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/identification-types/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_notFound() throws Exception {
        when(handler.delete(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/identification-types/1"))
                .andExpect(status().isNotFound());
    }
} 