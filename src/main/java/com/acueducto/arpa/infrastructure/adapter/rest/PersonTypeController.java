package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.PersonTypehandler;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/person-types")
public class PersonTypeController {
    private final PersonTypehandler personTypehandler;

    @Autowired
    public PersonTypeController(PersonTypehandler personTypehandler) {
        this.personTypehandler = personTypehandler;
    }

    @GetMapping
    public List<PersonTypeEntity> list() {
        return personTypehandler.list();
    }

    @PostMapping
    public PersonTypeEntity create(@RequestBody PersonTypeEntity type) {
        return personTypehandler.create(type);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonTypeEntity> update(@PathVariable Long id, @RequestBody PersonTypeEntity type) {
        return personTypehandler.update(id, type)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personTypehandler.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
} 