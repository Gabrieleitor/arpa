package com.acueducto.arpa.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "make")
public class MakeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public MakeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MakeEntity() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
