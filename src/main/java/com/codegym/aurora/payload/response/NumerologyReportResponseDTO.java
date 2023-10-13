package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumerologyReportResponseDTO {

    private String fullName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private LifePathResponseDTO lifePathResponseDTO;
    private DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO;
    private AttitudeNumberResponseDTO attitudeNumberResponseDTO;
//    private int attitudeNumber;
//    private int soulNumber;
//    private int personalityNumber;
//    private int missionNumber;
//    private int middleAgeNumber;
    private LifePhaseResponseDTO lifePhaseResponseDTO;
//    private int karmicDebt;
    private List<PersonalYearResponseDTO> personalYearResponseDTOList;
//    private int personalMonth;
//    private int pinnacleOfLife;
//    private int feelingInsideNumber;
//    private int defectNumberOfNameChart;
    private List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList;
//    private int numbersInChart;
//    private int balanceChart;
}
