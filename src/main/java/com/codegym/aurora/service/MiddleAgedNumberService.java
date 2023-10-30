package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;

public interface MiddleAgedNumberService {
    MiddleAgedNumberResponseDto getMiddleAgedNumberResponseDto(Integer middleAgedNumber);
    Integer calculateMiddleAgedNumber(Integer missionNumber, Integer lifePathNumber);

    MiddleAgedNumberResponseDto findMiddleAgeNumber(Integer missionNumber, Integer lifePathNumber);
}

