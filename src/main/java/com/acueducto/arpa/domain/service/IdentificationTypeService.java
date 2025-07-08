package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentificationTypeService {
    private final IdentificationTypeRepository identificationTypeRepository;

    public IdentificationTypeService(IdentificationTypeRepository identificationTypeRepository) {
        this.identificationTypeRepository = identificationTypeRepository;
    }

    public List<IdentificationTypeDto> list() {
        return identificationTypeRepository.list();
    }

    public IdentificationTypeDto create(IdentificationTypeDto IdentificationTypeDto) {
        return identificationTypeRepository.create(IdentificationTypeDto);
    }

    public Optional<IdentificationTypeDto> update(Long id, IdentificationTypeDto IdentificationTypeDto) {
        return identificationTypeRepository.update(id, IdentificationTypeDto);
    }

    public boolean delete(Long id) {
        return identificationTypeRepository.delete(id);
    }
}
