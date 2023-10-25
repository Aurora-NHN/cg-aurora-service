package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.MissionNumberConverter;
import com.codegym.aurora.payload.response.MissionNumberResponseDTO;
import com.codegym.aurora.service.MissionNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionNumberConverterImpl implements MissionNumberConverter {
    private final MissionNumberService missionNumberService;
    @Override
    public MissionNumberResponseDTO convertEntityToResponseDTO(int missionNumber) {
        MissionNumberResponseDTO MissionNumberResponseDTO = missionNumberService.getMissionNumberResponseDTO(missionNumber);
        return MissionNumberResponseDTO;
    }
}
