package com.codegym.aurora.service;

import com.codegym.aurora.payload.response.BalanceNumberResponseDTO;

public interface BalanceNumberService {
    BalanceNumberResponseDTO getBalanceNumberResponseDtoInStatic(int balanceNumber);

    int calculateBalanceNumber(String fullName);


}
