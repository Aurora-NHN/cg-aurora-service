package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.MiddleAgedNumberConverter;
import com.codegym.aurora.payload.response.MiddleAgedNumberResponseDto;
import com.codegym.aurora.service.MiddleAgedNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MiddleAgedNumberConverterImpl implements MiddleAgedNumberConverter {
    private final MiddleAgedNumberService middleAgedNumberService;

    @Override
    public MiddleAgedNumberResponseDto convertEntityToResponseDto(int middleAgedNumber) {
        MiddleAgedNumberResponseDto middleAgedNumberResponseDto = middleAgedNumberService
                .getMiddleAgedNumberResponseDto(middleAgedNumber);
        return middleAgedNumberResponseDto;
    }
}
