package com.codegym.aurora.converter.impl;

import com.codegym.aurora.converter.BalanceNumberConverter;
import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;
import com.codegym.aurora.service.BalanceNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceNumberConverterImpl implements BalanceNumberConverter {
    private final BalanceNumberService balanceNumberService;
    @Override
    public BalanceNumberResponseDTO convertEntityToResponseDto(int balanceNumber) {
        BalanceNumberResponseDTO balanceNumberResponseDTO = balanceNumberService
                .getBalanceNumberResponseDtoInStatic(balanceNumber);
        return balanceNumberResponseDTO;
    }
}
