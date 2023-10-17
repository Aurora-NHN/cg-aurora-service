package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.SoulNumberConverter;
import com.codegym.aurora.payload.response.SoulNumberResponseDTO;
import com.codegym.aurora.service.SoulNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SoulNumberConverterImpl implements SoulNumberConverter {
    private final SoulNumberService soulNumberService;
    @Override
    public SoulNumberResponseDTO convertSoulNumberToResponseDto(int soulNumber) {
        SoulNumberResponseDTO soulNumberResponseDTO = soulNumberService
                .getSoulNumberResponseDtoInStatic(soulNumber);
        return soulNumberResponseDTO;
    }
}
