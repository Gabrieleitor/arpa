package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.PersonTypehandler;
import com.acueducto.arpa.application.handler.dtos.request.PersonTypeRequest;
import com.acueducto.arpa.application.handler.dtos.response.PersonTypeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person-types")
public class PersonTypeController {
    private final PersonTypehandler personTypehandler;
    private static final Logger log = LoggerFactory.getLogger(PersonTypeController.class);

    @Autowired
    public PersonTypeController(PersonTypehandler personTypehandler) {
        this.personTypehandler = personTypehandler;
    }

    @GetMapping
    public List<PersonTypeResponse> list() {
        log.info("Received request to list person types");
        List<PersonTypeResponse> list = personTypehandler.list();
        log.info("Returning {} person types", list.size());
        return list;
    }

    @PostMapping
    public PersonTypeResponse create(@RequestBody PersonTypeRequest type) {
        log.info("Received request to create person type: {}", type);
        PersonTypeResponse response = personTypehandler.create(type);
        log.info("Person type created successfully: {}", response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonTypeResponse> update(@PathVariable Long id, @RequestBody PersonTypeRequest type) {
        log.info("Received request to update person type: id={}", id);
        return personTypehandler.update(id, type)
                .map(updated -> {
                    log.info("Person type updated successfully: {}", updated);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    log.warn("Person type not found for update: id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Received request to delete person type: id={}", id);
        if (!personTypehandler.delete(id)) {
            log.warn("Person type not found for deletion: id={}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Person type deleted successfully: id={}", id);
        return ResponseEntity.ok().build();
    }
} 