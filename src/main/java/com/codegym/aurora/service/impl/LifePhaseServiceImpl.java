package com.codegym.aurora.service.impl;

import com.codegym.aurora.entity.DataNumerologyReport;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;
import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;
import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;
import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;
import com.codegym.aurora.service.LifePhaseService;
import com.codegym.aurora.service.MatureStateNumberService;
import com.codegym.aurora.service.OldStateNumberService;
import com.codegym.aurora.service.YoungStateNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifePhaseServiceImpl implements LifePhaseService {
    private final YoungStateNumberService youngStateNumberService;
    private final MatureStateNumberService matureStateNumberService;
    private final OldStateNumberService oldStateNumberService;

    @Override
    public LifePhaseResponseDTO createLifePhase(DataNumerologyReport data) {
        YoungStateNumberResponseDTO youngStateNumber = youngStateNumberService
                .findYoungStateNumber(data);
        MatureStateNumberResponseDTO matureStateNumber = matureStateNumberService
                .findMatureStateNumer(data);
        OldStateNumberResponseDTO oldStateNumber = oldStateNumberService
                .findOldStateNumber(data);

        LifePhaseResponseDTO lifePhaseResponseDTO = new LifePhaseResponseDTO();
        lifePhaseResponseDTO.setYoungStateNumberResponseDTO(youngStateNumber);
        lifePhaseResponseDTO.setMatureStateNumberResponseDTO(matureStateNumber);
        lifePhaseResponseDTO.setOldStateNumberResponseDTO(oldStateNumber);

        return lifePhaseResponseDTO;
    }


}
