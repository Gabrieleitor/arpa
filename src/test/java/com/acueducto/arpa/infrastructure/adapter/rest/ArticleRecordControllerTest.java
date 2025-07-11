package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.ArticleRecordHandler;
import com.acueducto.arpa.application.handler.dtos.request.ArticleRecordRequest;
import com.acueducto.arpa.application.handler.dtos.response.ArticleRecordResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRecordController.class)
class ArticleRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ArticleRecordHandler handler;
    @Autowired
    private ObjectMapper objectMapper;

    private ArticleRecordRequest request;
    private ArticleRecordResponse response;

    @BeforeEach
    void setUp() {
        request = new ArticleRecordRequest(
                "Carlos",// name
                "Perez",// lastName
                1L,    // articleTypeId
                1L,    // identificationTypeId
                1L,    // personTypeId
                "Test", // comment
                1L, // makeId
                "987654", // identificationNumber
                "12345"// serialNumber
        );
        response = new ArticleRecordResponse(
                1L, // id
                "HP", // name
                "Perez", // lastName
                "12345", // serial
                "ENTRY", // status
                "Laptop", // articleTypeName
                "CC", // identificationTypeName
                "Empleado", // personTypeName
                "Test", // comment
                "2024-01-01T10:00:00", // createdAt
                "HP Inc.", // makerName
                "987654" // identificationNumber
        );
    }

    @Test
    void registerEntry_success() throws Exception {
        when(handler.registerEntry(any(ArticleRecordRequest.class))).thenReturn(response);
        mockMvc.perform(post("/api/articles/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HP"));
    }

    @Test
    void registerEntry_error() throws Exception {
        when(handler.registerEntry(any(ArticleRecordRequest.class))).thenThrow(new IllegalArgumentException("Identification type not found"));
        mockMvc.perform(post("/api/articles/entry")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Identification type not found"));
    }

    @Test
    void registerExit_success() throws Exception {
        when(handler.registerExit(eq(1L))).thenReturn(response);
        mockMvc.perform(post("/api/articles/1/exit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HP"));
    }

    @Test
    void registerExit_error() throws Exception {
        when(handler.registerExit(eq(1L))).thenThrow(new IllegalArgumentException("Article not found"));
        mockMvc.perform(post("/api/articles/1/exit"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Article not found"));
    }

    @Test
    void list_success() throws Exception {
        when(handler.list()).thenReturn(List.of(response));
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("HP"));
    }

} 