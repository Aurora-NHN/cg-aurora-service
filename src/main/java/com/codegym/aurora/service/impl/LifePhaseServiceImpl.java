package com.codegym.aurora.service.impl;

import com.codegym.aurora.converter.LifePhaseConverter;
import com.codegym.aurora.entity.LifePhase;
import com.codegym.aurora.repository.LifePhaseRepository;
import com.codegym.aurora.service.LifePhaseService;
import com.codegym.aurora.service.MatureStateNumberService;
import com.codegym.aurora.service.OldStateNumberService;
import com.codegym.aurora.service.YoungStateNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifePhaseServiceImpl implements LifePhaseService {
    private final LifePhaseRepository lifePhaseRepository;
    private final LifePhaseConverter lifePhaseConverter;
    private final YoungStateNumberService youngStateNumberService;
    private final MatureStateNumberService matureStateNumberService;
    private final OldStateNumberService oldStateNumberService;
    @Override
    public LifePhase caculateLifephase(int day, int month, int year) {
        int youngStateNumber = youngStateNumberService.calculateYoungStateNumber(month);
        int matureStateNumber = matureStateNumberService.calculateMatureStateNumber(day);
        int oldStateNumber = oldStateNumberService.calculateOldStateNumber(year);

        LifePhase lifePhase = new LifePhase();
        lifePhase.setYoungStateNumber(youngStateNumber);
        lifePhase.setMatureStateNumber(matureStateNumber);
        lifePhase.setOldStateNumber(oldStateNumber);
        return lifePhase;
    }

    @Override
    public LifePhase createLifePhase(int youngStateNumber, int matureStateNumber, int oldStateNumber) {
        LifePhase lifePhase = caculateLifephase(youngStateNumber, matureStateNumber, oldStateNumber);
        return lifePhaseRepository.save(lifePhase);
    }


}
