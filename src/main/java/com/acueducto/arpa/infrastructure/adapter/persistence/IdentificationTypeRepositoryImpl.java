package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.IdentificationTypeEntity;
import com.acueducto.arpa.domain.ports.repository.IdentificationTypeRepository;
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
    public Optional<IdentificationTypeEntity> update(Long id, IdentificationTypeEntity identificationTypeEntity) {
        return jpaIdentificationTypeRepository.findById(id)
                .map(existingType -> {
                    existingType.setName(identificationTypeEntity.getName());
                    return jpaIdentificationTypeRepository.save(existingType);
                });
    }

    @Override
    public List<IdentificationTypeEntity> list() {
        return jpaIdentificationTypeRepository.findAll();
    }

    @Override
    public IdentificationTypeEntity create(IdentificationTypeEntity identificationTypeEntity) {
        return jpaIdentificationTypeRepository.save(identificationTypeEntity);
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
    public Optional<IdentificationTypeEntity> findById(Long id) {
        return jpaIdentificationTypeRepository.findById(id);

    }
}
