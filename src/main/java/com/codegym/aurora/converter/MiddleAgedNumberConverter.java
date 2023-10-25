package com.codegym.aurora.converter;

import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;


public interface MiddleAgedNumberConverter {

    MiddleAgedNumberResponseDto convertEntityToResponseDto(int middleAgedNumber);
}
