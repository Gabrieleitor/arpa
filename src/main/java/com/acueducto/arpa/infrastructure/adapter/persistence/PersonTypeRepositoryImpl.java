package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.domain.model.dtos.PersonTypeDto;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.PersonTypeEntity;
import com.acueducto.arpa.domain.ports.repository.PersonTypeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.PersonTypeMapper;
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
    public Optional<PersonTypeDto> update(Long id, PersonTypeDto personTypeDto) {
        return jpaPersonTypeRepository.findById(id)
                .map(
                        existing -> {
                            PersonTypeEntity entity = PersonTypeMapper.toEntity(personTypeDto);
                            entity.setId(id);
                            return PersonTypeMapper.toDomain(jpaPersonTypeRepository.save(entity));
                        }
                );
    }

    @Override
    public List<PersonTypeDto> list() {
        return jpaPersonTypeRepository.findAll().stream()
                .map(PersonTypeMapper::toDomain)
                .toList();
    }

    @Override
    public PersonTypeDto create(PersonTypeDto personTypeDto) {
        return PersonTypeMapper.toDomain(jpaPersonTypeRepository.save(PersonTypeMapper.toEntity(personTypeDto)));
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
    public Optional<PersonTypeDto> findById(Long id) {
        return jpaPersonTypeRepository.findById(id)
                .map(PersonTypeMapper::toDomain);
    }
}
