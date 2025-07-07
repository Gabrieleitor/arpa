package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.IdentificationTypeHandler;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/identification-types")
public class IdentificationTypeController {
    private final IdentificationTypeHandler identificationTypeHandler;

    @Autowired
    public IdentificationTypeController(IdentificationTypeHandler identificationTypeHandler) {
        this.identificationTypeHandler = identificationTypeHandler;
    }

    @GetMapping
    public List<IdentificationTypeEntity> list() {
        return identificationTypeHandler.list();
    }

    @PostMapping
    public IdentificationTypeEntity create(@RequestBody IdentificationTypeEntity type) {
        return identificationTypeHandler.create(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdentificationTypeEntity> update(@PathVariable Long id, @RequestBody IdentificationTypeEntity type) {
        return identificationTypeHandler.update(id, type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!identificationTypeHandler.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
} 