package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LIFE_PATH_NUMBER", uniqueConstraints = @UniqueConstraint(columnNames = "INDICATORS"))

public class LifePathNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "INDICATORS")
    private int indicators;
    @Column(name = "OVERVIEW")
    private String overview;
    @Column(name = "NATURE")
    private String nature;

    @Column(name = "HOBBIES ")
    private String hobbies;
    @Column(name = "CAREER_DEVELOPMENT_ORIENTATION")
    private String careerDevelopmentOrientation;
    @Column(name = "JOB")
    private String job;
    @Column(name = "FINANCE")
    private String finance;
    @Column(name = "LOVE")
    private String love;
    @Column(name = "HEALTH")
    private String health;
    @Column(name = "KARMA")
    private String karma;
    @Column(name = "SELF_SUGGESTION")
    private String selfSuggestion;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IS_DELETED", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;


    @OneToMany(mappedBy = "lifePathNumber", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<NumeroloryReport> numeroloryReportList = new ArrayList<>();
}
