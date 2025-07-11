package com.acueducto.arpa.domain.ports.repository;

import com.acueducto.arpa.domain.model.dtos.MakeDto;

import java.util.List;
import java.util.Optional;

public interface MakeRepository {
    Optional<MakeDto> update(Long id, MakeDto makeDto);

    List<MakeDto> list();

    MakeDto create(MakeDto makeDto);

    boolean delete(Long id);

    Optional<MakeDto> findById(Long id);
}
