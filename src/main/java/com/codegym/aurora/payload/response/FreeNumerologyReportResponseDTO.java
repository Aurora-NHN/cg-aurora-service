package com.codegym.aurora.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeNumerologyReportResponseDTO {

    private String fullName;
    private String nickName;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;

    private LifePathResponseDTO lifePathResponseDTO;
    private AttitudeNumberResponseDTO attitudeNumberResponseDTO;
    private MissionNumberResponseDTO missionNumberResponseDtTO;

}
