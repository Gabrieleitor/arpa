package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.application.handler.dtos.request.MakeRequest;
import com.acueducto.arpa.application.handler.dtos.response.MakeResponse;
import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.service.MakeService;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.MakeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeHandler {
    private final MakeService makeService;
    private static final Logger log = LoggerFactory.getLogger(MakeHandler.class);

    public MakeHandler(MakeService makeService) {
        this.makeService = makeService;
    }

    public List<MakeResponse> list() {
        log.info("Handler: Listing all makes");
        try {
            List<MakeResponse> list = makeService.findAll().stream()
                    .map(MakeMapper::toResponse)
                    .toList();
            log.info("Handler: Found {} makes", list.size());
            return list;
        } catch (Exception e) {
            log.error("Handler: Error listing makes", e);
            throw e;
        }
    }

    public MakeResponse create(MakeRequest makeRequest) {
        log.info("Handler: Creating make: {}", makeRequest);
        try {
            MakeDto makeDto = MakeMapper.toDomain(makeRequest);
            MakeResponse response = MakeMapper.toResponse(makeService.create(makeDto));
            log.info("Handler: Make created successfully: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Handler: Error creating make", e);
            throw e;
        }
    }

    public Optional<MakeResponse> update(Long id, MakeRequest makeRequest) {
        log.info("Handler: Updating make: id={}", id);
        try {
            MakeDto makeDto = MakeMapper.toDomain(makeRequest);
            Optional<MakeResponse> updated = makeService.update(id, makeDto)
                    .map(MakeMapper::toResponse);
            if (updated.isPresent()) {
                log.info("Handler: Make updated successfully: id={}", id);
            } else {
                log.warn("Handler: Make not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Handler: Error updating make: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Handler: Deleting make: id={}", id);
        try {
            boolean deleted = makeService.delete(id);
            if (deleted) {
                log.info("Handler: make deleted successfully: id={}", id);
            } else {
                log.warn("Handler: make not found for deletion: id={}", id);
            }
            return deleted;
        } catch (Exception e) {
            log.error("Handler: Error deleting make: id={}", id, e);
            throw e;
        }
    }
}
