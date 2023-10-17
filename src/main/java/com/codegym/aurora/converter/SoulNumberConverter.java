package com.codegym.aurora.converter;

import com.codegym.aurora.payload.response.SoulNumberResponseDTO;

public interface SoulNumberConverter {
    SoulNumberResponseDTO convertSoulNumberToResponseDto(int soulNumber);
}
