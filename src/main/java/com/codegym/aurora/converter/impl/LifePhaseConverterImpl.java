package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.LifePhaseConverter;
import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.payload.response.LifePhaseResponseDTO;
import com.codegym.aurora.payload.response.MatureStateNumberResponseDTO;
import com.codegym.aurora.payload.response.OldStateNumberResponseDTO;
import com.codegym.aurora.payload.response.YoungStateNumberResponseDTO;
import com.codegym.aurora.service.MatureStateNumberService;
import com.codegym.aurora.service.OldStateNumberService;
import com.codegym.aurora.service.YoungStateNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LifePhaseConverterImpl implements LifePhaseConverter {
    private final YoungStateNumberService youngStateNumberService;
    private final MatureStateNumberService matureStateNumberService;
    private final OldStateNumberService oldStateNumberService;

    @Override
    public LifePhaseResponseDTO convertEntityToResponseDto(LifePhase lifePhase) {
        LifePhaseResponseDTO lifePhaseResponseDTO = new LifePhaseResponseDTO();
        YoungStateNumberResponseDTO youngStateNumberResponseDTO = youngStateNumberService.
                getYoungStateNumber(lifePhase.getYoungStateNumber());
        MatureStateNumberResponseDTO matureStateNumberResponseDTO = matureStateNumberService
                .getMatureStateNumber(lifePhase.getMatureStateNumber());
        OldStateNumberResponseDTO oldStateNumberResponseDTO = oldStateNumberService
                .getOldStateNumber(lifePhase.getOldStateNumber());

        lifePhaseResponseDTO.setId(lifePhase.getId());
        lifePhaseResponseDTO.setYoungStateNumberResponseDTO(youngStateNumberResponseDTO);
        lifePhaseResponseDTO.setMatureStateNumberResponseDTO(matureStateNumberResponseDTO);
        lifePhaseResponseDTO.setOldStateNumberResponseDTOr(oldStateNumberResponseDTO);
        return lifePhaseResponseDTO;
    }
}
