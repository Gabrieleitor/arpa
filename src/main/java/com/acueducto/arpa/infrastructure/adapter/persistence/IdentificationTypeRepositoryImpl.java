package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.domain.model.dtos.IdentificationTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.IdentificationTypeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class IdentificationTypeRepositoryImpl implements IdentificationTypeRepository {

    private final JpaIdentificationTypeRepository jpaIdentificationTypeRepository;

    public IdentificationTypeRepositoryImpl(JpaIdentificationTypeRepository jpaIdentificationTypeRepository) {
        this.jpaIdentificationTypeRepository = jpaIdentificationTypeRepository;
    }

    @Override
    public Optional<IdentificationTypeDto> update(Long id, IdentificationTypeDto identificationType) {
        return jpaIdentificationTypeRepository.findById(id)
                .map(existing -> {
                    IdentificationTypeEntity entity = IdentificationTypeMapper.toEntity(identificationType);
                    entity.setId(id);
                    return IdentificationTypeMapper.toDomain(jpaIdentificationTypeRepository.save(entity));
                });
    }

    @Override
    public List<IdentificationTypeDto> list() {
        return jpaIdentificationTypeRepository.findAll().stream()
                .map(IdentificationTypeMapper::toDomain)
                .toList();
    }

    @Override
    public IdentificationTypeDto create(IdentificationTypeDto identificationType) {
        return IdentificationTypeMapper.toDomain(
                jpaIdentificationTypeRepository.save(IdentificationTypeMapper.toEntity(identificationType))
        );
    }

    @Override
    public boolean delete(Long id) {
        return jpaIdentificationTypeRepository.findById(id)
                .map(existingType -> {
                    jpaIdentificationTypeRepository.delete(existingType);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<IdentificationTypeDto> findById(Long id) {
        return jpaIdentificationTypeRepository.findById(id)
                .map(IdentificationTypeMapper::toDomain);
    }
}
