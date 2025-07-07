package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonTypeRepositoryImpl implements PersonTypeRepository {
    private JpaPersonTypeRepository jpaPersonTypeRepository;

    public PersonTypeRepositoryImpl(JpaPersonTypeRepository jpaPersonTypeRepository) {
        this.jpaPersonTypeRepository = jpaPersonTypeRepository;
    }

    @Override
    public Optional<PersonTypeEntity> update(Long id, PersonTypeEntity identificationType) {
        return jpaPersonTypeRepository.findById(id)
                .map(existingType -> {
                    existingType.setName(identificationType.getName());
                    return jpaPersonTypeRepository.save(existingType);
                });
    }

    @Override
    public List<PersonTypeEntity> list() {
        return jpaPersonTypeRepository.findAll();
    }

    @Override
    public PersonTypeEntity create(PersonTypeEntity identificationType) {
        return jpaPersonTypeRepository.save(identificationType);
    }

    @Override
    public boolean delete(Long id) {
        return jpaPersonTypeRepository.findById(id)
                .map(existingType -> {
                    jpaPersonTypeRepository.delete(existingType);
                    return true;
                })
                .orElse(false);
    }
    @Override
    public Optional<PersonTypeEntity> findById(Long id) {
        return jpaPersonTypeRepository.findById(id);
    }
}
