package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PINNACLE_OF_LIFE")
public class PinnacleOfLife {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PINNACLE_OF_LIFE_NUMBER")
    private int pinnacleOfLifeNumber;

    @Column(name = "AGE")
    private int age;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "NUMEROLOGY_REPORT_ID",referencedColumnName = "ID")
    private NumerologyReport numerologyReport;

    public PinnacleOfLife(int pinnacleOfLifeNumber, int age) {
        this.pinnacleOfLifeNumber = pinnacleOfLifeNumber;
        this.age = age;
    }
}
