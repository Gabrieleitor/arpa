package com.acueducto.arpa.application.handler;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.domain.service.IdentificationTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdentificationTypeHandler {

    private final IdentificationTypeService identificationTypeService;

    public IdentificationTypeHandler(IdentificationTypeService identificationTypeService) {
        this.identificationTypeService = identificationTypeService;
    }

    public List<IdentificationTypeEntity> list() {
        return identificationTypeService.list();
    }

    public IdentificationTypeEntity create(IdentificationTypeEntity identificationTypeEntity) {
        return identificationTypeService.create(identificationTypeEntity);
    }

    public Optional<IdentificationTypeEntity> update(Long id, IdentificationTypeEntity identificationTypeEntity) {
        return identificationTypeService.update(id, identificationTypeEntity);
    }

    public boolean delete(Long id) {
        return identificationTypeService.delete(id);
    }
}
