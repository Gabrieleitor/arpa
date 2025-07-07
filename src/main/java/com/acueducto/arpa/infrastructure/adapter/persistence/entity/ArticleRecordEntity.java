package com.acueducto.arpa.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.acueducto.arpa.domain.model.vo.Name;
import com.acueducto.arpa.domain.model.vo.Make;
import com.acueducto.arpa.domain.model.vo.Serial;
import com.acueducto.arpa.domain.model.vo.Comment;

@Entity
public class ArticleRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    private IdentificationTypeEntity identificationTypeEntity;

    @ManyToOne(optional = false)
    private PersonTypeEntity personTypeEntity;

    @ManyToOne(optional = false)
    private ArticleTypeEntity articleTypeEntity;


    @Column(name = "make")
    private String make;

    @Column(name = "serial")
    private String serial;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleStatus status;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    public enum ArticleStatus {
        ENTRY, EXIT
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

    public void setName(Name name) {
        this.name = name.getValue();
    }

    public IdentificationTypeEntity getIdentificationType() {
        return identificationTypeEntity;
    }

    public void setIdentificationType(IdentificationTypeEntity identificationTypeEntity) {
        this.identificationTypeEntity = identificationTypeEntity;
    }

    public PersonTypeEntity getPersonType() {
        return personTypeEntity;
    }

    public void setPersonType(PersonTypeEntity personTypeEntity) {
        this.personTypeEntity = personTypeEntity;
    }

    public ArticleTypeEntity getArticleType() {
        return articleTypeEntity;
    }

    public void setArticleType(ArticleTypeEntity articleTypeEntity) {
        this.articleTypeEntity = articleTypeEntity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make.getValue();
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial.getValue();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment.getValue();
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }
} 