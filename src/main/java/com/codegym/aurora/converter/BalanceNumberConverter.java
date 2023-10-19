package com.codegym.aurora.converter;

import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;

public interface BalanceNumberConverter {
    BalanceNumberResponseDTO convertEntityToResponseDto(int balanceNumber);
}
