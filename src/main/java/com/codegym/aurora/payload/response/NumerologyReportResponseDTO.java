package com.codegym.aurora.payload.response;

import com.codegym.aurora.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumerologyReportResponseDTO {
    private Long id;
    private String fullName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private User user;
    private LifePathResponseDTO lifePathResponseDTO;
    private DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO;
//    private int attitudeNumber;
//    private int soulNumber;
//    private int personalityNumber;
//    private int missionNumber;
//    private int middleAgeNumber;
//    private int lifePhase;
//    private int karmicDebt;
//    private int personalYear;
//    private int personalMonth;
//    private int pinnacleOfLife;
//    private int feelingInsideNumber;
//    private int defectNumberOfNameChart;
//    private int challengeNumber;
//    private int numbersInChart;
//    private int balanceChart;
}
