package com.codegym.aurora.service;

import com.codegym.aurora.entity.PinnacleOfLife;
import com.codegym.aurora.payload.response.PinnacleOfLifeResponseDTO;

import java.util.List;

public interface PinnacleOfLifeService {

    PinnacleOfLifeResponseDTO getPinnacleOfLifeResponseDtoInStaticList(int pinnacleOfLifeNumber);
    int calculatePinnacleOfLifeFirst(int month, int day);
    int calculatePinnacleOfLifeSecond(int day, int year);
    int calculatePinnacleOfLifeThird(int pinnacleOfLifeFirst, int pinnacleOfLifeSecond );
    int calculatePinnacleOfLifeFour(int month, int year);

    int calculateAgeForPinnacleOfLifeFirst(int lifePathNumber);
    int calculateAgeForOrtherPinnacleOfLife(int age);
    List<PinnacleOfLife> createPinnacOfLifeEntity(int day, int month, int year, int lifePathNumber);

}
