package com.codegym.aurora.service;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;

public interface LifePhaseService {

    LifePhaseResponseDTO  createLifePhase(DataNumerologyReport data);
}
