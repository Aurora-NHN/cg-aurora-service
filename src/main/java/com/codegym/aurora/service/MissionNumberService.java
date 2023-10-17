package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.MissionNumberResponseDTO;

public interface MissionNumberService {
    MissionNumberResponseDTO getMissionNumberResponseDTO(int missionNumber);
    int calculateMissionNumber(String fullName);
}
