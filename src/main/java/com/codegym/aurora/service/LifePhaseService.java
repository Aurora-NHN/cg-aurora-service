package com.codegym.aurora.service;

import com.codegym.aurora.entity.LifePhase;

public interface LifePhaseService {
    LifePhase caculateLifephase(int day, int month, int year);

    LifePhase createLifePhase(int youngStateNumber, int matureStateNumber, int oldStateNumber);
}
