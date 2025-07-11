package com.acueducto.arpa.infrastructure.adapter.rest;

import com.acueducto.arpa.application.handler.MakeHandler;
import com.acueducto.arpa.application.handler.dtos.request.MakeRequest;
import com.acueducto.arpa.application.handler.dtos.response.MakeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/makes")
public class MakeController {
    private final MakeHandler makeHandler;
    private static final Logger log = LoggerFactory.getLogger(MakeController.class);

    @Autowired
    public MakeController(MakeHandler makeHandler) {
        this.makeHandler = makeHandler;
    }

    @GetMapping
    public List<MakeResponse> list() {
        log.info("Received request to list makes");
        List<MakeResponse> list = makeHandler.list();
        log.info("Returning {} makes", list.size());
        return list;
    }

    @PostMapping
    public MakeResponse create(@RequestBody MakeRequest type) {
        log.info("Received request to create make: {}", type);
        MakeResponse response = makeHandler.create(type);
        log.info("Make created successfully: {}", response);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MakeResponse> update(@PathVariable Long id, @RequestBody MakeRequest type) {
        log.info("Received request to update make: id={}", id);
        return makeHandler.update(id, type)
                .map(updated -> {
                    log.info("Make updated successfully: {}", updated);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    log.warn("Person type not found for update: id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Received request to delete make: id={}", id);
        if (!makeHandler.delete(id)) {
            log.warn("Make not found for deletion: id={}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Make deleted successfully: id={}", id);
        return ResponseEntity.ok().build();
    }
}
