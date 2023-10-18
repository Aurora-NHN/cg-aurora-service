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
@Builder
@Table(name = "PERSONAL_MONTH")
public class PersonalMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "PERSONAL_MONTH_NUMBER")
    private int personalMonthNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PERSONAL_YEAR_ID",referencedColumnName = "ID")
    private PersonalYear personalYear;

    public PersonalMonth(int personalMonthNumber) {
        this.personalMonthNumber = personalMonthNumber;
    }

    public PersonalMonth(int month, int personalMonthNumber) {
        this.month = month;
        this.personalMonthNumber = personalMonthNumber;
    }
}
