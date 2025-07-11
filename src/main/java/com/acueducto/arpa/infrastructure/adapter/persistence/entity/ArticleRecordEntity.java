package com.acueducto.arpa.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "article_record")
public class ArticleRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(optional = false)
    private IdentificationTypeEntity identificationTypeEntity;

    @ManyToOne(optional = false)
    private PersonTypeEntity personTypeEntity;

    @ManyToOne(optional = false)
    private ArticleTypeEntity articleTypeEntity;

    @ManyToOne(optional = false)
    private MakeEntity make;

    @Column(name = "serial")
    private String serial;

    @Column(name = "comment")
    private String comment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleStatusEnum status;

    @Column(nullable = false)
    private LocalDateTime entryDate;

    @Column(nullable = false)
    private String identificationNumber;

    private LocalDateTime exitDate;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IdentificationTypeEntity getIdentificationTypeEntity() {
        return identificationTypeEntity;
    }

    public void setIdentificationTypeEntity(IdentificationTypeEntity identificationTypeEntity) {
        this.identificationTypeEntity = identificationTypeEntity;
    }

    public PersonTypeEntity getPersonTypeEntity() {
        return personTypeEntity;
    }

    public void setPersonTypeEntity(PersonTypeEntity personTypeEntity) {
        this.personTypeEntity = personTypeEntity;
    }

    public ArticleTypeEntity getArticleTypeEntity() {
        return articleTypeEntity;
    }

    public void setArticleTypeEntity(ArticleTypeEntity articleTypeEntity) {
        this.articleTypeEntity = articleTypeEntity;
    }

    public MakeEntity getMake() {
        return make;
    }

    public void setMake(MakeEntity make) {
        this.make = make;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArticleStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ArticleStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }
}