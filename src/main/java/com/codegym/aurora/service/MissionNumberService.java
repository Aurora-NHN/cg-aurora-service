package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.payload.response.MissionNumberResponseDTO;

public interface MissionNumberService {
    MissionNumberResponseDTO getMissionNumberResponseDTO(int missionNumber);

    MissionNumberResponseDTO findMissionNumber(NumerologyReportRequestDTO requestDTO);
    MissionNumberResponseDTO findMissionNumber(DataNumerologyReport data);
}
