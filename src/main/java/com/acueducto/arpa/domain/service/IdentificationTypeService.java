package com.acueducto.arpa.domain.service;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
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

    public List<IdentificationTypeEntity> list() {
        return identificationTypeRepository.list();
    }

    public IdentificationTypeEntity create(IdentificationTypeEntity identificationTypeEntity) {
        return identificationTypeRepository.create(identificationTypeEntity);
    }

    public Optional<IdentificationTypeEntity> update(Long id, IdentificationTypeEntity identificationTypeEntity) {
        return identificationTypeRepository.update(id, identificationTypeEntity);
    }

    public boolean delete(Long id) {
        return identificationTypeRepository.delete(id);
    }
}
