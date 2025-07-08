package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.IdentificationTypeHandler;
import com.acueducto.arpa.application.handler.dtos.request.IdentificationTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.IdentificationTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/identification-types")
public class IdentificationTypeController {
    private final IdentificationTypeHandler identificationTypeHandler;
    private static final Logger log = LoggerFactory.getLogger(IdentificationTypeController.class);

    @Autowired
    public IdentificationTypeController(IdentificationTypeHandler identificationTypeHandler) {
        this.identificationTypeHandler = identificationTypeHandler;
    }

    @GetMapping
    public List<IdentificationTypeResponse> list() {
        log.info("Received request to list identification types");
        List<IdentificationTypeResponse> list = identificationTypeHandler.list();
        log.info("Returning {} identification types", list.size());
        return list;
    }

    @PostMapping
    public ResponseEntity<IdentificationTypeResponse> create(@RequestBody IdentificationTypeRequest type) {
        log.info("Received request to create identification type: {}", type);
        IdentificationTypeResponse identification = identificationTypeHandler.create(type);
        log.info("Identification type created successfully: {}", identification);
        return ResponseEntity.ok(identification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdentificationTypeResponse> update(@PathVariable Long id, @RequestBody IdentificationTypeRequest type) {
        log.info("Received request to update identification type: id={}", id);
        return identificationTypeHandler.update(id, type)
                .map(updated -> {
                    log.info("Identification type updated successfully: {}", updated);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    log.warn("Identification type not found for update: id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Received request to delete identification type: id={}", id);
        if (!identificationTypeHandler.delete(id)) {
            log.warn("Identification type not found for deletion: id={}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Identification type deleted successfully: id={}", id);
        return ResponseEntity.ok().build();
    }
} 