package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleTypeHandler;
import com.acueducto.arpa.application.handler.dtos.request.ArticleTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleTypeResponse;
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

@WebMvcTest(ArticleTypeController.class)
class ArticleTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ArticleTypeHandler handler;
    @Autowired
    private ObjectMapper objectMapper;

    private ArticleTypeRequest request;
    private ArticleTypeResponse response;

    @BeforeEach
    void setUp() {
        request = new ArticleTypeRequest("Laptop");
        response = new ArticleTypeResponse(1L, "Laptop");
    }

    @Test
    void list_success() throws Exception {
        when(handler.list()).thenReturn(List.of(response));
        mockMvc.perform(get("/api/article-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void create_success() throws Exception {
        when(handler.create(any())).thenReturn(response);
        mockMvc.perform(post("/api/article-types")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void update_success() throws Exception {
        when(handler.update(eq(1L), any())).thenReturn(Optional.of(response));
        mockMvc.perform(put("/api/article-types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    void update_notFound() throws Exception {
        when(handler.update(eq(1L), any())).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/article-types/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_success() throws Exception {
        when(handler.delete(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/article-types/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_notFound() throws Exception {
        when(handler.delete(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/article-types/1"))
                .andExpect(status().isNotFound());
    }
} 