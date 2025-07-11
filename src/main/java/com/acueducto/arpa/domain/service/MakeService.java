package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.ports.repository.MakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeService {
    private static final Logger log = LoggerFactory.getLogger(MakeService.class);
    private final MakeRepository makeRepository;

    public MakeService(MakeRepository makeRepository) {
        this.makeRepository = makeRepository;
    }

    public List<MakeDto> findAll() {
        log.info("Listing all makes");
        try {
            List<MakeDto> makes = makeRepository.list();
            log.info("Found {} makes", makes.size());
            return makes;
        } catch (Exception e) {
            log.error("Error listing makes", e);
            throw e;
        }
    }

    public MakeDto create(MakeDto makeDto) {
        log.info("Creating make: {}", makeDto);
        try {
            MakeDto created = makeRepository.create(makeDto);
            log.info("Make created successfully: {}", created);
            return created;
        } catch (Exception e) {
            log.error("Error creating make", e);
            throw e;
        }
    }

    public Optional<MakeDto> update(Long id, MakeDto makeDto) {
        log.info("Updating make: id={}", id);
        try {
            Optional<MakeDto> updated = makeRepository.update(id, makeDto);
            if (updated.isPresent()) {
                log.info("Make updated successfully: id={}", id);
            } else {
                log.warn("Make not found for update: id={}", id);
            }
            return updated;
        } catch (Exception e) {
            log.error("Error updating make: id={}", id, e);
            throw e;
        }
    }

    public boolean delete(Long id) {
        log.info("Deleting make: id={}", id);
        try {
            boolean delete = makeRepository.delete(id);
            log.info("Make deleted successfully: id={}", id);
            return delete;
        } catch (Exception e) {
            log.error("Error deleting make: id={}", id, e);
            throw e;
        }
    }
}
