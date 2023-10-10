package com.codegym.aurora.entity;

import lombok.AllArgsConstructor;
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
@Table(name = "NUMEROLOGY_REPORT")
public class NumerologyReport{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "DAY_OF_BIRTH")
    private int dayOfBirth;

    @Column(name = "MONTH_OF_BIRTH")
    private int monthOfBirth;

    @Column(name = "YEAR_OF_BIRTH")
    private int yearOfBirth;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private User user;

    @Column(name = "LIFE_PATH_NUMBER")
    private int lifePathNumber;
    @Column(name = "DAY_OF_BIRTH_NUMBER")
    private int dayOfBirthNumber;
    @Column(name = "ATTITUDE_NUMBER")
    private int attitudeNumber;
    @Column(name = "SOUL_NUMBER")
    private int soulNumber;
    @Column(name = "PERSONALITY_NUMBER")
    private int personalityNumber;
    @Column(name = "MISSION_NUMBER")
    private int missionNumber;
    @Column(name = "MIDDLE_AGE_NUMBER")
    private int middleAgeNumber;
    @Column(name = "LIFE_PHASE")
    private int lifePhase;
    @Column(name = "KARMIC_DEBT")
    private int karmicDebt;
    @Column(name = "PERSONAL_YEAR")
    private int personalYear;
    @Column(name = "PERSONAL_MONTH")
    private int personalMonth;
    @Column(name = "PINNACLE_OF_LIFE")
    private int pinnacleOfLife;
    @Column(name = "FEELING_INSIDE_NUMBER")
    private int feelingInsideNumber;
    @Column(name = "DEFECT_NUMBER_OF_NAME_CHART")
    private int defectNumberOfNameChart;
    @Column(name = "CHALLENGE_NUMBER")
    private int challengeNumber;
    @Column(name = "NUMBERS_IN_CHART")
    private int numbersInChart;
    @Column(name = "BALANCE_CHART")
    private int balanceChart;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;

}
