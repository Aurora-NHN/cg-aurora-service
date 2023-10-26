package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NumerologyReportResponseDTO {
    private Long id;
    private String fullName;
    private String nickName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private LocalDateTime createTime;

    private List<MeanOfNumberResponseDTO> meanOfNumberResponseDTOList;
    private UserResponseDtoForNumerologyReport userResponseDtoForNumerologyReport;

    private LifePathResponseDTO lifePathResponseDTO;
    private MissionNumberResponseDTO missionNumberResponseDtTO;
    private AttitudeNumberResponseDTO attitudeNumberResponseDTO;
    private DayOfBirthNumberResponseDTO dayOfBirthNumberResponseDTO;
    private SoulNumberResponseDTO soulNumberResponseDTO;
    private MiddleAgedNumberResponseDto middleAgedNumberResponseDto;
    private BalanceNumberResponseDTO balanceNumberResponseDTO;
    private LifePhaseResponseDTO lifePhaseResponseDTO;

    private List<ChallengeNumberResponseDTO> challengeNumberResponseDTOList;
    private List<PersonalYearResponseDtoForReport> personalYearResponseDTOList;
    private List<PinnacleOfLifeResponseDTOForReport> pinnacleOfLifeResponseDTOForReportList;

}
