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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PERSONAL_YEAR")
public class PersonalYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PERSONAL_YEAR_NUMBER")
    private int personalYearNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "NUMEROLOGY_REPORT_ID",referencedColumnName = "ID")
    private NumerologyReport numerologyReport;

    @OneToMany(mappedBy = "personalYear", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<PersonalMonth> personalMonthList = new ArrayList<>();

    public PersonalYear(int personalYearNumber) {
        this.personalYearNumber = personalYearNumber;
    }

    public PersonalYear(int personalYearNumber, List<PersonalMonth> personalMonthList) {
        this.personalYearNumber = personalYearNumber;
        this.personalMonthList = personalMonthList;
    }
}
