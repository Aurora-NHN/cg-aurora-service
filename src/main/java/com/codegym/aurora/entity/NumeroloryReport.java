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
@Table(name = "NUMEROLORY_REPORT")
public class NumeroloryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "DAY_OF_BIRTH", nullable = false)
    private int dayOfBirth;

    @Column(name = "MONTH_OF_BIRTH", nullable = false)
    private int monthOfBirth;

    @Column(name = "YEAR_OF_BIRTH", nullable = false)
    private int yearOfBirth;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Column(name = "IS_ACTIVATED", columnDefinition = "boolean default true")
    private boolean isActivated;

    //    Mối quan hệ với các entity khác
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "LIFE_PATH_NUMBER_ID",referencedColumnName = "ID")
    private LifePathNumber lifePathNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "DAY_OF_BIRTH_NUMBER_ID",referencedColumnName = "ID")
    private DayOfBirthNumber dayOfBirthNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ATTITUDE_NUMBER_ID",referencedColumnName = "ID")
    private AttitudeNumber attitudeNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "SOUL_NUMBER_ID",referencedColumnName = "ID")
    private SoulNumber soulNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PERSONALITY_NUMBER_ID",referencedColumnName = "ID")
    private PersonalityNumber personalityNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "MISSION_NUMBER_ID",referencedColumnName = "ID")
    private MissionNumber missionNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "MIDDLE_AGE_NUMBER_ID",referencedColumnName = "ID")
    private MiddleAgeNumber middleAgeNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "LIFE_PHASE_ID",referencedColumnName = "ID")
    private LifePhase lifePhase;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "KARMIC_DEBT_ID",referencedColumnName = "ID")
    private KarmicDebt karmicDebt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PERSONAL_YEAR_ID",referencedColumnName = "ID")
    private PersonalYear personalYear;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PERSONAL_MONTH_ID",referencedColumnName = "ID")
    private PersonalMonth personalMonth;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "PINNACLE_OF_LIFE_ID",referencedColumnName = "ID")
    private PinnacleOfLife pinnacleOfLife;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "FEELING_INSIDE_NUMBER_ID",referencedColumnName = "ID")
    private FeelingInsideNumber feelingInsideNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "DEFECT_NUMBER_OF_NAME_CHART_ID",referencedColumnName = "ID")
    private DefectNumberOfNameChart defectNumberOfNameChart;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "CHALLENGE_NUMBER_ID",referencedColumnName = "ID")
    private ChallegeNumber challegeNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "NUMBERS_IN_CHART_ID",referencedColumnName = "ID")
    private NumbersInChart numbersInChart;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "BALANCE_CHART_ID",referencedColumnName = "ID")
    private BalanceChart balanceChart;
}
