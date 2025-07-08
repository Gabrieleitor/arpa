package com.acueducto.arpa.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person_type")
public class PersonTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public PersonTypeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PersonTypeEntity() {
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