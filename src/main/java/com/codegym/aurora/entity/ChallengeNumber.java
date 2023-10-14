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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CHALLENGE_NUMBER")
public class ChallengeNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CHALLENGE_NUMBER_1")
    private int challengeNumberFirst;
    @Column(name = "CHALLENGE_NUMBER_2")
    private int challengeNumberSecond;
    @Column(name = "CHALLENGE_NUMBER_3")
    private int challengeNumberThird;
    @Column(name = "CHALLENGE_NUMBER_4")
    private int challengeNumberFour;

    @OneToOne(mappedBy = "challengeNumber", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private NumerologyReport numerologyReport;
}
