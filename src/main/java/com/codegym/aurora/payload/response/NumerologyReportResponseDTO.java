package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NumerologyReportResponseDTO {

    private String fullName;
    private String nickName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private LifePathResponseDTO lifePathResponseDTO;
    private DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO;
    private AttitudeNumberResponseDTO attitudeNumberResponseDTO;
    private SoulNumberResponseDTO soulNumberResponseDTO;
    private MissionNumberResponseDTO missionNumberResponseDtTO;
    private MiddleAgedNumberResponseDto middleAgedNumberResponseDto;
    private LifePhaseResponseDTO lifePhaseResponseDTO;
    private List<PersonalYearResponseDtoForReport> personalYearResponseDTOList;
    private List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeResponseDTOForReportList;
    private List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList;
    private BalanceNumberResponseDTO balanceNumberResponseDTO;
}
