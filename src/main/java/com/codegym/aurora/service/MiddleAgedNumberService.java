package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;

public interface MiddleAgedNumberService {
    MiddleAgedNumberResponseDto getMiddleAgedNumberResponseDto(int middleAgedNumber);
    int calculateMiddleAgedNumber(int missionNumber, int lifePathNumber);
}

