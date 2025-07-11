package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.domain.model.dtos.MakeDto;
import com.acueducto.arpa.domain.ports.repository.MakeRepository;
import com.acueducto.arpa.infrastructure.adapter.persistence.entity.MakeEntity;
import com.acueducto.arpa.infrastructure.adapter.persistence.mapper.MakeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MakeRepositoryImpl implements MakeRepository {

    private final JpaMakeRepository jpaMakeRepository;

    public MakeRepositoryImpl(JpaMakeRepository jpaMakeRepository) {
        this.jpaMakeRepository = jpaMakeRepository;
    }


    @Override
    public Optional<MakeDto> update(Long id, MakeDto makeDto) {
        return jpaMakeRepository.findById(id)
                .map(existing -> {
                    MakeEntity updatedMake = MakeMapper.toEntity(makeDto);
                    updatedMake.setId(id);
                    return  MakeMapper.toDomain(jpaMakeRepository.save(updatedMake));
                });
    }

    @Override
    public List<MakeDto> list() {
        return jpaMakeRepository.findAll().stream()
                .map(MakeMapper::toDomain)
                .toList();
    }

    @Override
    public MakeDto create(MakeDto makeDto) {
        return MakeMapper.toDomain(jpaMakeRepository.save(MakeMapper.toEntity(makeDto)));
    }

    @Override
    public boolean delete(Long id) {
        return jpaMakeRepository.findById(id)
                .map(existingMake -> {
                    jpaMakeRepository.delete(existingMake);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<MakeDto> findById(Long id) {
        return jpaMakeRepository.findById(id)
                .map(MakeMapper::toDomain);
    }
}
