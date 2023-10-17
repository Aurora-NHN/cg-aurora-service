package com.codegym.aurora.converter;

import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;

public interface LifePhaseConverter {

    LifePhaseResponseDTO convertEntityToResponseDto(LifePhase lifePhase);
}
