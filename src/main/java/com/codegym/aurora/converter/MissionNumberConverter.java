package com.codegym.aurora.converter;

import com.codegym.aurora.payload.response.MissionNumberResponseDTO;

public interface MissionNumberConverter {
    MissionNumberResponseDTO convertEntityToResponseDTO(int missionNumber);
}
