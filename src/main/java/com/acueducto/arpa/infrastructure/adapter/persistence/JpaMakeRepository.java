package com.acueducto.arpa.infrastructure.adapter.persistence;

import com.acueducto.arpa.infrastructure.adapter.persistence.entity.MakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMakeRepository extends JpaRepository<MakeEntity, Long> {
}
